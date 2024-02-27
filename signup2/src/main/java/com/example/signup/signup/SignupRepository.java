package com.example.signup.signup;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignupRepository extends JpaRepository<com.example.signup.signup.SignupModel, Long> {
    boolean existsByEmail(String email);
    @Autowired
    com.example.signup.signup.SignupModel findByVerificationToken(String token);
    Optional<com.example.signup.signup.SignupModel> findByEmail(String email); //for login function
}
