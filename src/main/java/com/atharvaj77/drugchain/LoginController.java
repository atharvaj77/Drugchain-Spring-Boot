package com.atharvaj77.drugchain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final FirebaseAuth firebaseAuth;

    public LoginController(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("idToken") String idToken,
                               Model model) {

        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            String userId = decodedToken.getUid();

            // Retrieve user details from Firestore or your user service based on the userId
            User user = getUserService().getUserById(userId);

            if (user != null) {
                // Set user details in session or authentication context as needed

                if (user.getUserType().equals("manufacturer")) {
                    return "redirect:/manufacturer?id=" + userId;
                } else if (user.getUserType().equals("distributor")) {
                    return "redirect:/distributor?id=" + userId;
                } else if (user.getUserType().equals("pharmacist")) {
                    return "redirect:/pharma?id=" + userId;
                }
            } else {
                model.addAttribute("error", "user-not-found");
                return "login";
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }
}
