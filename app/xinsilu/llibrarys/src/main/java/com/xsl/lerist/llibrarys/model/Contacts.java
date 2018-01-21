package com.xsl.lerist.llibrarys.model;

import java.util.ArrayList;

/**
 * Created by Lerist on 2016/4/13, 0013.
 */
public class Contacts {
    private ArrayList<String> phones = new ArrayList<>();
    private String name;
    private String email;

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public void addPhone(String phone) {
        phones.add(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "phones=" + phones +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
