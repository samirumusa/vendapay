package com.example.vendaa.service;

import com.example.vendaa.BillPaymentAdvise;
import com.example.vendaa.BillPaymentStatus;
import com.example.vendaa.BouquetsListItems;
import com.example.vendaa.CustomerValidation;
// this is a class that hold all the function we are going to take to post to the server
// it is a inherited and implemented by QuickTellerSVAImpl
// each variable of the functions is returned object of a particular class
// for example customer validation required customer ID and paymeny code
//however, we have to get the ID and code from the customer from and set it in the below function
//where we would send it to the server.
// interface is a dummy the actual operation is done in the implementation.

public interface QuickTellerSVA {
    CustomerValidation customerValidation(CustomerValidation customerValidationRequest) throws Exception;

    BillPaymentAdvise paymentAdvise(BillPaymentAdvise billPaymentAdvise) throws Exception;

    BillPaymentStatus billPaymentStatus(String reference) throws Exception;

    BouquetsListItems getBouquet(String alias) throws Exception;

    BouquetsListItems getAirtime(String alias) throws Exception;

    BouquetsListItems getDataPlans(String alias) throws Exception;
}

