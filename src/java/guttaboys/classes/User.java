/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guttaboys.classes;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class User implements Serializable{
    private int usernr;
    private String email;
    private String firstName;
    private String surName;
    private String username;
    private String password;
    private String confirmemail;
    private String confirmpassword;
    private boolean isEmployee;
    
    
    

    public User(int usernr, String email, String firstName, String surName, String userName, String password, boolean isEmployee) {
        this.usernr = usernr;
        this.email = email;
        this.firstName = firstName;
        this.surName = surName;
        this.username = userName;
        this.password = password;
        this.isEmployee = isEmployee;
    }

    public boolean isIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }
   
    
    public String getConfirmemail() {
        return confirmemail;
    }
 
    public void setConfirmemail(String confirmemail) {
        this.confirmemail = confirmemail;
    }
    
    public String getConfirmpassword() {
        return confirmpassword;
    }
 
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    public void setUsername(String NewValue){
        this.username = NewValue;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){return password;}
    public void setPassword(String newValue){
        this.password = newValue;
    }
    
    public void setUsernr(int usernr) {
        this.usernr = usernr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getUsernr() {
        return usernr;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
    
}
