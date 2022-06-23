package com.example.vendaa;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

//this is method use to tell the billing company about the payment
public class CustomerValidation {
    @JsonAlias({"Customers", "customers"})
    private List<Customers> customers;

    public CustomerValidation(){

    }

    public CustomerValidation(List<Customers> customers){
        this.customers = customers;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }
}

