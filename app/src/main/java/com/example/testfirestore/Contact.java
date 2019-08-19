package com.example.testfirestore;

import com.google.firebase.firestore.Exclude;

public class Contact {
    @Exclude private String id;

    private String name;
    private String sip;

    public Contact(){

    }

    public Contact(String name, String sip){
        this.name = name;
        this.sip = sip;
    }

    //public String getId(){return id;}
    //public void setId(String id) { this.id = id; }
    public String getName(){ return name; }
    public String getSip(){ return sip; }
}
