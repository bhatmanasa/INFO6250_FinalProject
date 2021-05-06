/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.Stats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Manasa
 */
@Entity
@Table(name="user_country_tbl")
public class UserCountry {
        @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    int ucId;
     @Column(name="country")   
    String country;
     @Column(name="countUser")   
    int countUser;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountUser() {
        return countUser;
    }

    public void setCountUser(int countUser) {
        this.countUser = countUser;
    }
    
}
