package com.pverge.core;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable {

    private Long contactId;

    @NotNull @Size(max=25)
    private String firstName;

    @NotNull
    @Size(max=25)
    private String lastName;

    @Size(max=40)
    private String companyName;

    public Contact() {
    }

    public Contact(Long contactId, String firstName, String lastName, String companyName) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}	