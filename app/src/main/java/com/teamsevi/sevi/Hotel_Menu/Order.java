package com.teamsevi.sevi.Hotel_Menu;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order {

    public String name;
    public float price;
    public int qty;


    public Order() {
    }

    public Order(String name,float price,int qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getPrice(){ return price; }
    public void setPrice(float price){ this.price = price; }

    public int getQty(){ return qty; }
    public void setQty(int qty){ this.qty = qty; }

}
