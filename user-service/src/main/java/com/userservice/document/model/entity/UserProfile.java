package com.userservice.document.model.entity;

import com.userservice.document.model.valueobjects.Contact;
import com.userservice.document.model.valueobjects.Location;
import com.userservice.document.model.valueobjects.PersonalInfo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProfile {

    private Long id;
    private PersonalInfo personalInfo;
    private Contact contact;
    private Location location;

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


}
