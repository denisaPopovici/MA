import 'package:flutter/material.dart';

class Drug{
    
    Drug(this.id, this.name, this.price, this.provider, this.producer, this.releaseYear, this.quantity);

    String id;
    String name;
    double price;
    String provider;
    String producer;
    int releaseYear;
    int quantity;

    int getQuantity(){
        return quantity;
    }

    String getName(){
        return name;
    }

    String getId(){
        return id;
    }

    double getPrice(){
        return price;
    }

     String getProvider(){
        return provider;
    }

     String getProducer(){
        return producer;
    }

    int getReleaseYear(){
        return releaseYear;
    }

    void setName(String name){
        this.name = name;
    }

    void setProvider(String provider){
        this.provider = provider;
    }

    void setPrice(double price){
        this.price = price;
    }

    void setProducer(String producer){
        this.producer = producer;
    }

    void setReleaseYear(int releaseYear){
        this.releaseYear =  releaseYear;
    }

    void setQuantity(int quantity){
        this.quantity = quantity;
    }

}