package com.example.h_saveme;

public class Item {

    String name;
    //String status;
    // String address;


    public Item(String name) {
        this.name = name;
        // this.status = status;
        //this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

       /* public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }*/
}
