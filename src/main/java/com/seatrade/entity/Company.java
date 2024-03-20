package com.seatrade.entity;

public class Company {
    private String name;
    private int width;
    private int height;
    private int companyId;

    private  double companyBalance;

    public Company(String name, int companyId, double companyBalance) {

        this.name = name;
        this.companyId = companyId;
        this.companyBalance = companyBalance;
    }

    public Company(String name, double companyBalance) {
        this.name = name;
        this.companyBalance = companyBalance;
    }

    public Company(String name) {
        this.name = name;
        this.companyBalance= 20000;
        this.width=10;
        this.height=10;
    }

    public Company(int companyId) {
        this.companyId=companyId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyBalance(double companyBalance) {
        this.companyBalance = companyBalance;
    }

    public double getCompanyBalance() {
        return companyBalance;
    }
}
