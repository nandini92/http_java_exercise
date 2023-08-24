package com.httpdemo;

public class Tshirt {
    private int small = 45;
    private int medium = 0;
    private int large = 21;
    private int xlarge = 19;

    @Override
    public String toString() {
        return "Tshirt [small=" + small + ", medium=" + medium + ", large=" + large + ", xlarge=" + xlarge + "]";
    }

    public Tshirt() {
    }

    public int getSmall() {
        return small;
    }
    public void setSmall(int small) {
        this.small = small;
    }
    public int getMedium() {
        return medium;
    }
    public void setMedium(int medium) {
        this.medium = medium;
    }
    public int getLarge() {
        return large;
    }
    public void setLarge(int large) {
        this.large = large;
    }
    public int getXlarge() {
        return xlarge;
    }
    public void setXlarge(int xlarge) {
        this.xlarge = xlarge;
    }

    
}
