package com.example.pokemon2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    private long id;

    private  String name;
    private  String number;

    @SerializedName("type")
    @Expose
    private Type_ type;

    public Pokemon(long id, String name, String number, Type_ type){
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Type_ getType() {
        return type;
    }

    public void setType(Type_ type) {
        this.type = type;
    }
}
