package com.userservice.document.model.commands;

import com.userservice.document.model.valueobjects.Contact;
import com.userservice.document.model.valueobjects.Location;
import com.userservice.document.model.valueobjects.PersonalInfo;
import com.userservice.document.model.valueobjects.UserCredentials;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


public class CreateUserCommand {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String mobile;
    private String email;
    private String address;
    private String zipCode;
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

    public UserCredentials extractUserCredentials() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(this.username);
        userCredentials.setPassword(this.password);
        return userCredentials;
    }

    public Contact extractContact() {
        Contact contact = new Contact();
        contact.setMobileNumber(this.mobile);
        contact.setEmail(this.email);
        return contact;
    }

    public Location extractLocation() {
        Location location = new Location();
        location.setZipCode(this.zipCode);
        location.setAddress(this.address);
        return location;
    }

    public PersonalInfo extractPersonInfo() {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setLastName(this.lastName);
        personalInfo.setFirstName(this.firstName);
        personalInfo.setBirthDate(this.birthDate);
        return personalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserCommand that = (CreateUserCommand) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, birthDate, mobile, email, address, zipCode, roles);
    }

    public static class Builder {

        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String mobile;
        private String email;
        private String address;
        private String zipCode;
        private Set<String> roles;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setRoles(Set<String> roles) {
            this.roles = roles;
            return this;
        }


        public CreateUserCommand build() {
            CreateUserCommand createUserCommand = new CreateUserCommand();
            createUserCommand.setRoles(this.roles);
            createUserCommand.setZipCode(this.zipCode);
            createUserCommand.setAddress(this.address);
            createUserCommand.setEmail(this.email);
            createUserCommand.setMobile(this.mobile);
            createUserCommand.setBirthDate(this.birthDate);
            createUserCommand.setLastName(this.lastName);
            createUserCommand.setLastName(this.firstName);
            createUserCommand.setPassword(this.password);
            createUserCommand.setUsername(this.username);
            return createUserCommand;
        }

    }
}
