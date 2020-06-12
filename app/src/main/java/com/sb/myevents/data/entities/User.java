package com.sb.myevents.data.entities;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.data.entities
 * My Events
 */
public class User {

    private String name;
    private String lastName;
    private String phone;
    private String mail;
    private String password;

    public User(String name, String lastName, String phone, String mail, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
