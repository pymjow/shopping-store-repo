package com.userservice.document.model.entity;

import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.valueobjects.Contact;
import com.userservice.document.model.valueobjects.Location;
import com.userservice.document.model.valueobjects.PersonalInfo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserProfile {

    private Long id;
    private PersonalInfo personalInfo;
    private Contact contact;
    private Location location;
    private User user;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    @Embedded
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToOne(mappedBy = "userProfile")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
