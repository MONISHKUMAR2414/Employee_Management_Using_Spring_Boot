package com.bmt.MyStore.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;


    private String phone;

    private String address;
    @Size(min = 6 , message = "Minimum password length is 6 Characters")

    private String password;

    private String confirmPassword;

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @Size(min = 6, message = "Minimum password length is 6 Characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Minimum password length is 6 Characters") String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }
}
