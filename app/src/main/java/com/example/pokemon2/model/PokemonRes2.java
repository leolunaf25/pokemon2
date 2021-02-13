package com.example.pokemon2.model;

import java.util.ArrayList;

public class PokemonRes2 {

    private String base_experience;
    private String name;
    private  String height;
    private  String weight;
    private ArrayList<Pokemon> types;

    public String getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(String base_experience) {
        this.base_experience = base_experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ArrayList<Pokemon> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Pokemon> types) {
        this.types = types;
    }

}
