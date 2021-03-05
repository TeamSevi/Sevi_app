package com.teamsevi.sevi.Model;

public class Model_menu {
    private  String itemname;
    private  String itemdescription;
    private  String itemprice;
Model_menu()
{

}

    public Model_menu(String itemname, String itemdescription, String itemprice) {
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemprice = itemprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }
}
