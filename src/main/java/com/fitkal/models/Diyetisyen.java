package com.fitkal.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection="Diyetisyen")
public class Diyetisyen {

    private String diyetisyenId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private boolean active;
    private ArrayList<String> danisanId;
    private Set<Role> roles = new HashSet<>();
    //private ArrayList<String> mesajKutusu;
    public Diyetisyen(String diyetisyenId,ArrayList<String> danisanId) {
        this.diyetisyenId= diyetisyenId;
        this.danisanId=danisanId;
        //this.mesajKutusu=mesajKutusu;
    }
    public Diyetisyen(){}

    public Diyetisyen( String email, String password) {

        this.email = email;
        this.password = password;
    }
    public Diyetisyen(String email, String password, String name, String surname, String phone, Date date) {

        this.email=email;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.phone=phone;

    }

    public String getDiyetisyenId() {
        return diyetisyenId;
    }
    public ArrayList <String> getDanisanId() {
        return danisanId;
    }
    //public ArrayList<String> getMesajKutusu() {
    //    return mesajKutusu;
    //}
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;

    }



}
