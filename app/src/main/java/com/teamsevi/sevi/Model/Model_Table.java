package com.teamsevi.sevi.Model;

public class Model_Table {
    private String name;
    private String status;
Model_Table(){

}

    public Model_Table(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
