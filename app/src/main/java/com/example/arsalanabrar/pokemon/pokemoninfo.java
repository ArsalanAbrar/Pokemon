package com.example.arsalanabrar.pokemon;

public class pokemoninfo {
    private String hieght, order,baseexp,weight;


    public pokemoninfo() {
    }

    public pokemoninfo(String hieght, String order,String baseexp,String weight) {
        this.hieght = hieght;
        this.order = order;
        this.baseexp=baseexp;
        this.weight=weight;

    }

    public String getHieght() {
        return hieght;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getBaseexp() {
        return baseexp;
    }

    public void setBaseexp(String baseexp) {
        this.baseexp = baseexp;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
