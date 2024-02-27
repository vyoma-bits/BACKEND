package com.example.signup.signup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignupService {
    //Suggestion to make below fields 'final'
    //Good practice when you have a field that's injected via a constructor and won't change during the lifetime of the object.
    //Making it final ensures it won't be accidentally modified.

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final com.example.signup.signup.SignupRepository signupRepository;
    private final JavaMailSender javaMailSender;

    public SignupService(com.example.signup.signup.SignupRepository signupRepository, JavaMailSender javaMailSender) {
        this.signupRepository = signupRepository;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //User registration method
    public boolean registerUser(com.example.signup.signup.SignupModel signupModel) {
        if (signupRepository.existsByEmail(signupModel.getEmail())) {
            return false;
        }

        //logging email & ownerId received from frontend, for testing purposes
        String rawEmail = signupModel.getEmail();
        System.out.println("Received email: " + rawEmail);

        String rawOrderFrequency = signupModel.getOrderFrequency();
        System.out.println("Received OrderFrequency: " + rawOrderFrequency);

        // Validate password
        String rawPassword = signupModel.getPassword();
        if (rawPassword == null || rawPassword.isEmpty()) {
            System.err.println("Password cannot be empty");
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Log the received password
        System.out.println("Received password: " + rawPassword);

        //Encrypt password using bcrypt
        signupModel.setPassword(passwordEncoder.encode(signupModel.getPassword()));
        signupModel.setVerificationToken(UUID.randomUUID().toString());
        signupModel.setIsVerified(false);

        signupRepository.save(signupModel);

        // send verification email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(signupModel.getEmail());
        mailMessage.setSubject("Verify your email address");
        mailMessage.setText("Please click the link below to verify your email address: \n"
                + "http://localhost:8080/verify-email?token=" + signupModel.getVerificationToken());
        javaMailSender.send(mailMessage);
        return true;
    }

    public boolean verifyEmail(String token) {
        com.example.signup.signup.SignupModel signupModel = signupRepository.findByVerificationToken(token);
        if (signupModel == null) {
            return false;
        }
        signupModel.setIsVerified(true);
        signupModel.setVerificationToken(null);
        signupRepository.save(signupModel);
        return true;
    }
}
