package com.atharvaj77.drugchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DrugchainController {

    @Autowired
    private DrugchainService drugchainService;


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
        //TODO: Write Create Product Logic for manufacturer
        return "create_product";
    }

    @GetMapping("/manufacturer/qrViewer/{id}")
    public String showQr(@PathVariable String id){
        return "qr_page";
    }

    @GetMapping("/manufacturer/requests")
    public String manufacturerRequests(){
        return "show_requests";
    }

    @GetMapping("/manufacturer/inventory")
    public String manufacturerInventory(){
        return "inventory";
    }

    //Distributor Handler
    @GetMapping("/distributor")
    public String distributorPage(){
        return "distributor";
    }

    @GetMapping("/distributor/createProduct")
    public String distributorCreateProduct(){
        //TODO: Write Create Product Logic for distributor
        return "dcreate_product";
    }

    @GetMapping("/distributor/makeRequest")
    public String distributorMakeRequest(){
        return "make_request";
    }

    @GetMapping("/distributor/requests")
    public String distrubutorRequests(){
        return  "dshowRequests";
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

    @GetMapping("/pharmacist/makeRequest")
    public String pharmacistMakeRequest(){
        return "make_request";
    }

    @GetMapping("/pharmacist/inventory")
    public String pharmacistInventory(){
        return "inventory";
    }

}
