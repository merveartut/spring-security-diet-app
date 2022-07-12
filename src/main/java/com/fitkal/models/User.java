package com.fitkal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "User")
public class User  {
   @Id
    private String _id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String weight;
    private String height;
    private String phone;
    private Date date;
    private String healthProblem;
    private String allergy;
    private boolean active;
    private Set<Role> roles = new HashSet<>();


public User(){}
    public User( String email, String password) {

        this.email = email;
        this.password = password;
    }

public User(String email,String password,String name,String surname,String phone,Date date,String weight,String height,String healthProblem,String allergy) {

    this.email=email;
    this.password=password;
    this.name=name;
    this.surname=surname;
    this.phone=phone;
    this.date=date;
    this.weight=weight;
    this.height=height;
    this.healthProblem=healthProblem;
    this.allergy=allergy;
}
    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail(){return email;}
    public String getPassword() {
        return password;
    }
    public String getWeight(){return weight;}
    public String getHeight(){return height;}
    public String getPhone(){return phone;}
    public Date getDate(){return date;}
    public String getHealthProblem(){return healthProblem;}
    public String getAllergy(){return allergy;}


    public void setPassword(String password) {
        this.password = password;
    }
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

    public void setEmail(String email) {
    this.email=email;
    }
}
