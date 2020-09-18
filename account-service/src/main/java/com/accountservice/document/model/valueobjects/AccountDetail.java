package com.accountservice.document.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Embeddable
public class AccountDetail {

    private LocalDate creationDate;
    private Boolean locked;

    @Column
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Column
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @PrePersist
    public void init() {
        this.creationDate = LocalDate.now();
    }

}
