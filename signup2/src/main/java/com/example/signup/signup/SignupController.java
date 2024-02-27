package com.example.signup.signup;



import com.example.signup.signup.SignupModel;
import com.example.signup.signup.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    //Cross-origin declaration to allow requests from the frontend origin.
    @CrossOrigin(origins = "*")

    //POST call for user registration
    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupModel signupModel) {
        boolean isRegistered = signupService.registerUser(signupModel);
        //Map object to send HTTP request in json format
        Map<String, String> response = new HashMap<>();
        if (!isRegistered) {
            response.put("message", "Email already in use");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "User registered successfully. Verify your email before login");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //verifyEmail endpoint method
    @GetMapping("/user-verify-email")
    public ResponseEntity<Object> verifyEmail(@RequestParam("token") String token) {
        boolean isVerified = signupService.verifyEmail(token);
        if (!isVerified) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Email verified successfully", HttpStatus.OK);
    }
}
