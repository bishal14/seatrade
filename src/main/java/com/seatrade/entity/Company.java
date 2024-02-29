package com.seatrade.entity;

public class Company {
    private String name;
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
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public double getCompanyBalance() {
        return companyBalance;
    }
}
