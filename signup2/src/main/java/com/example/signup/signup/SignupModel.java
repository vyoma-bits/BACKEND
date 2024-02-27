package com.example.signup.signup;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "cbs-users")
public class SignupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    public String getEmail() {
        return email;
    }


    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }


    private String verificationToken;


    public SignupModel() {
    }

    // getters and setters added along with declared properties
    //Json keys added to properties that were displayed null or 0 in MySQL tables, success

    @Column(name = "verification_token")
    public String getVerificationToken() {
        return verificationToken;
    }
    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    @Column(name = "CompanyName")
    private String companyName;
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName() {return companyName;}

    @Column(name = "RepresentativeFirstName")
    @JsonProperty("firstName")
    private String firstName;
    public String getRepresentativeFirstName() {
        return firstName;
    }
    public void setRepresentativeFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "RepresentativeLastName")
    @JsonProperty("lastName")
    private String lastName;
    public String getRepresentativeLastName() {
        return lastName;
    }
    public void setRepresentativeLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "OrderFrequency")
    @JsonProperty("orderFrequency")
    private String orderFrequency;
    public String getOrderFrequency() {    return orderFrequency;}
    public void setOrderFrequency(String orderFrequency) {this.orderFrequency = orderFrequency;}

    @Column(name = "CompanyType")
    @JsonProperty("companyType")
    private String companyType;
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Column(name = "ContactDetails")
    @JsonProperty("mobile")
    private String mobile;
    public String getContactDetails() {
        return mobile;
    }
    public void setContactDetails(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "BillingAddress")
    @JsonProperty("billAddress")
    private String billAddress;
    public String getBillingAddress() {
        return billAddress;
    }
    public void setBillingAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    @Column(name = "AadhaarNo")
    @JsonProperty("aadhaar")
    private String aadhaar;
    public String getAadhaarNo() {
        return aadhaar;
    }
    public void setAadhaarNo(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    @Column(name = "GST")
    @JsonProperty("gst")
    private String gst;
    public String getGST() {
        return gst;
    }
    public void setGST(String gst) {
        this.gst = gst;
    }


    //added getters and setters for fields to avoid field not null error response on postman request

    @Column(name = "PAN")
    @JsonProperty("pan")
    private String pan;
    public String getPAN() {
        return pan;
    }
    public void setPAN(String pan) {
        this.pan = pan;
    }

    @Column(name = "is_verified")
    private boolean isVerified;
    // getters and setters
    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    public boolean getIsVerified() {
        return isVerified;
    }
}
