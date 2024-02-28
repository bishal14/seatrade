package com.seatrade.entity;

public class Company {
    private String name;
    private int companyId;

    public Company(String name, int companyId) {

        this.name = name;
        this.companyId = companyId;
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
}
