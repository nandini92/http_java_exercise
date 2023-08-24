package com.httpdemo;

public class Stock {
    private int bottle = 229;
    private int socks = 342;
    private Tshirt tshirt;
    
    @Override
    public String toString() {
        return "Stock [bottle=" + bottle + ", socks=" + socks + ", tshirt=" + tshirt + "]";
    }

    public Stock() {
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    public int getSocks() {
        return socks;
    }

    public void setSocks(int socks) {
        this.socks = socks;
    }

    public Tshirt getTshirt() {
        return tshirt;
    }

    public void setTshirt(Tshirt tshirt) {
        this.tshirt = tshirt;
    }

    
}