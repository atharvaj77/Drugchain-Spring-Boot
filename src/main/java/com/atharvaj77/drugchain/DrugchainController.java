package com.atharvaj77.drugchain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class DrugchainController {
    DrugchainService drugchainService;
    BlockchainService blockchainService;


    @Autowired
    DrugchainController(DrugchainService drugchainService, BlockchainService blockchainService) {
        this.drugchainService = drugchainService;
        this.blockchainService = blockchainService;
    }


    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(){
        return "";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }


    //Manufacturer Handler
    @GetMapping("/manufacturer")
    public String manufacturerPage(){
        return "manufacturer";
    }

    @GetMapping("/manufacturer/createProduct")
    public String manufacturerCreateProduct(){
        return "create_product";
    }

    @PostMapping("/manufacturer/createProduct")
    public String createProductPage(@RequestParam("req_id") String requestId,
                                    @RequestParam("manufacturer-date") String mfgDate,
                                    @RequestParam("expiry-date") String expiryDate,
                                    @RequestParam("batch-number") String batchNo) throws Exception {

        // Retrieve data from the database or other sources as needed
        Map<String,Object> requestData = drugchainService.getPendingRequestData(requestId);
        String itemName = requestData.get("productName").toString();
        int numberUnits = Integer.parseInt( requestData.get("numOfItem").toString());
        //TODO: Implement user id logic here
        //String userId = // Retrieve the user ID from session
        //User user = drugchainService.getUser(getSessionId());
        User user = drugchainService.getUser("Wxi9MY3LQbezsJlcDNc6P6JXyaw2");

        String hist = String.format("manufacturer: %s, address: %s, transferred_to: %s, address: %s, time: %s",
                user.getUsername(), user.getPublicAddress(), requestData.get("req_from"),
                requestData.get("web3Address_from"), LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss")));

        String transactionId = blockchainService.createProduct(itemName, mfgDate, expiryDate, batchNo,
                BigInteger.valueOf(numberUnits), hist, user.getPublicAddress(), requestData.get("web3Address_from").toString());

        BigInteger pid = blockchainService.getLastProductId(requestData.get("web3Address_from").toString());
        blockchainService.updateHistory(BigInteger.valueOf(Long.parseLong(String.valueOf(pid)) + 1), transactionId, user.getPublicAddress());
        drugchainService.deletePendingRequest(requestId);

        // Return the appropriate response
        return "create_product";
    }

    @GetMapping("/manufacturer/qrViewer/{id}")
    public String showQR(@PathVariable String id, Model model) {
        System.out.println("showQR called");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(id, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        String imgHtmlBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
        model.addAttribute("imgHtmlBase64", imgHtmlBase64);
        return "qr_page";
    }

    @GetMapping("/manufacturer/requests")
    public String manufacturerRequests(Model model) throws ExecutionException, InterruptedException {
        //String manufacturerId = getSessionId();
        String manufacturerId = "Wxi9MY3LQbezsJlcDNc6P6JXyaw2";

        List<Map<String,Object>> pendingRequests = drugchainService.getPendingRequests(manufacturerId);

        model.addAttribute("requests", pendingRequests);
        return "show_requests";
    }

    @GetMapping("/manufacturer/inventory")
    public String manufacturerInventory(Model model){
        return "inventory";
    }

    @GetMapping("/createProductPage")
    public String createProductPage(@RequestParam String req_id, Model model){
        model.addAttribute("notValidProduct",false);
        return "create_product";
    }

    //Distributor Handler
    @GetMapping("/distributor")
    public String distributorPage(){
        return "distributor";
    }

    @GetMapping("/distributor/createProduct")
    public String showCreateProductPage(Model model, @RequestParam("req_id") String requestId) throws Exception {
        String userId = getSessionId();
        User user = drugchainService.getUser(userId);
        List allProducts = blockchainService.getAllProducts(user.getPublicAddress());

        Map<String, Object> requestData = drugchainService.getPendingRequestData(requestId);
        if (requestData == null) {
            return "You have no pending requests";
        }

        String name = (String) requestData.get("productName");
        int items = Integer.parseInt((String) requestData.get("numOfItem"));

        Map<String, Object> validProduct = null;

        /*for (Object item : allProducts) {
            if (item.get("name").equals(name) && Integer.parseInt((String) item.get("numberUnits")) > items) {
                validProduct = (Map<String, Object>) item;
                break;
            }
        }*/

        if (validProduct == null) {
            model.addAttribute("notvalidProduct", true);
            return "dcreate_product";
        } else {
            model.addAttribute("validProduct", validProduct);
            return "dcreate_product";
        }
    }

    @PostMapping("/distributor/createProduct")
    public String processCreateProduct(@RequestParam("req_id") String requestId,
                                       @RequestParam("batch-number") String batchNo,
                                       Model model) {

        try {
            User user = drugchainService.getUser(getSessionId());
            Map<String, Object> requestData = drugchainService.getPendingRequestData(requestId);
            String itemName = (String) requestData.get("productName");
            String mfgDate = (String) requestData.get("mfgDate");
            String expiryDate = (String) requestData.get("expiryDate");
            String parHist = (String) requestData.get("par_hist");
            int numberUnits = Integer.parseInt((String) requestData.get("numOfItem"));

            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss"));

            String hist = parHist + "," + String.format("transferred_to: %s, address: %s, time: %s",
                    requestData.get("req_from"), requestData.get("web3Address_from"), now);

            String transactionId = blockchainService.createProduct(itemName, mfgDate, expiryDate, batchNo,
                    BigInteger.valueOf(numberUnits), parHist, user.getPublicAddress(), (String) requestData.get("web3Address_from"));

            drugchainService.deletePendingRequest(requestId);

            String pid = String.valueOf(blockchainService.getLastProductId((String) requestData.get("web3Address_from")));

            blockchainService.updateHistory(BigInteger.valueOf(Long.parseLong(pid)), hist, user.getPublicAddress());

            model.addAttribute("submitted", true);
            model.addAttribute("p_id", pid);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/distributor/createProduct?req_id=" + requestId;
    }

    @GetMapping("/distributor/makeRequest")
    public String showMakeRequest(Model model) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> manufacturers = drugchainService.getManufacturers();
        User user = drugchainService.getUser("QXbVfYLuZ8NQvqx960FzlRP5mS23");
        String userType = user.getUserType();

        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("userType", userType);

        return "make_request";
    }

    @PostMapping("/distributor/makeRequest")
    public String distributorMakeRequest(@RequestParam("item-name") String itemName,
                                     @RequestParam("manufacturer_id") String manufacturerId,
                                     @RequestParam("additional_message") String additionalMessage,
                                     @RequestParam("number-units") String numOfUnits) {

        try {
            //User user = drugchainService.getUser(getSessionId());
            User user = drugchainService.getUser("QXbVfYLuZ8NQvqx960FzlRP5mS23");

            Map<String, Object> data = new java.util.HashMap<String, Object>(Map.of(
                    "req_from", user.getUsername(),
                    "productName", itemName,
                    "numOfItem", numOfUnits,
                    "to_id", manufacturerId,
                    "web3Address_from", user.getPublicAddress(),
                    "message", additionalMessage
            ));

            String requestId = drugchainService.createPendingRequest(data);

            if (requestId != null) {
                data.put("req_id", requestId);
                drugchainService.updatePendingRequest(requestId, data);
            } else {
                System.out.println("Error creating pending request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/distributor/makeRequest";
    }


    @GetMapping("/distributor/requests")
    public String showPendingRequests(Model model) throws ExecutionException, InterruptedException {
        String distributorId = getSessionId();

        List<Map<String,Object>> pendingRequests = drugchainService.getPendingRequests(distributorId);

        model.addAttribute("requests", pendingRequests);
        return "dshow_requests";
    }

    @GetMapping("/distributor/inventory")
    public String distributorInventory(){
        return "inventory";
    }

    //Pharmacist Handler
    @GetMapping("/pharmacist")
    public String pharmacistPage(){
        return "pharmacist";
    }

    @GetMapping("/pharma/makerequest")
    public String pmakeRequest(Model model) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> distributors = drugchainService.getDistributors();

        User user = drugchainService.getUser(getSessionId());
        String userType = user.getUserType();

        model.addAttribute("manufacturers", distributors);
        model.addAttribute("userType", userType);

        return "make_request";
    }

    @PostMapping("/pharma/makerequest")
    public String pharmacistMakeRequest(@RequestParam("item-name") String itemName,
                                     @RequestParam("manufacturer_id") String distributorId,
                                     @RequestParam("additional_message") String additionalMessage,
                                     @RequestParam("number-units") String numOfUnits) {

        try {
            User user = drugchainService.getUser(getSessionId());

            Map<String, Object> data = new java.util.HashMap<String, Object>(Map.of(
                    "req_from", user.getUsername(),
                    "productName", itemName,
                    "numOfItem", numOfUnits,
                    "to_id", distributorId,
                    "web3Address_from", user.getPublicAddress(),
                    "message", additionalMessage
            ));

            String requestId = drugchainService.createPendingRequest(data);

            if (requestId != null) {
                data.put("req_id", requestId);
                drugchainService.updatePendingRequest(requestId, data);
            } else {
                System.out.println("Error creating pending request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/pharma/makerequest";
    }

    @GetMapping("/pharma/inventory")
    public String pshowInventory(Model model) throws Exception {
        User user = drugchainService.getUser(getSessionId());
        List productData = blockchainService.getAllProducts(user.getPublicAddress());

        model.addAttribute("productData", productData);
        return "inventory";
    }

    private String getSessionId() {
        // TODO: Implement session logic
        return null;
    }

}
