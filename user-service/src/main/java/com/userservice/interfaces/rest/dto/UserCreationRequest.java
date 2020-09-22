package com.userservice.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class UserCreationRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String email;
    @NotEmpty
    private String address;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationRequest request = (UserCreationRequest) o;
        return Objects.equals(username, request.username) &&
                Objects.equals(password, request.password) &&
                Objects.equals(firstName, request.firstName) &&
                Objects.equals(lastName, request.lastName) &&
                Objects.equals(birthDate, request.birthDate) &&
                Objects.equals(mobile, request.mobile) &&
                Objects.equals(email, request.email) &&
                Objects.equals(address, request.address) &&
                Objects.equals(zipCode, request.zipCode) &&
                Objects.equals(roles, request.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, birthDate, mobile, email, address, zipCode, roles);
    }
}
