package com.fitkal.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SignupRequestDiy {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String role ;
    private String name;
    private String surname;

    private String phone;
    private Date date;




    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return "user";
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getPhone(){return phone;}
    public Date getDate(){return date;}

}
