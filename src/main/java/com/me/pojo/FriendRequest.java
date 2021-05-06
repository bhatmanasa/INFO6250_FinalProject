/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;

/**
 *
 * @author Manasa
 */
public class FriendRequest {
    int id;
    int recepient;
    int requestor;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecepient() {
        return recepient;
    }

    public void setRecepient(int recepient) {
        this.recepient = recepient;
    }

    public int getRequestor() {
        return requestor;
    }

    public void setRequestor(int requestor) {
        this.requestor = requestor;
    }
    
}
