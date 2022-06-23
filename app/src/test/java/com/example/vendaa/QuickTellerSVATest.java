package com.example.vendaa;
import com.example.vendaa.service.QuickTellerSVA;
import com.example.vendaa.service.impl.QuickTellerSVAImpl;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class QuickTellerSVATest {

    private String reference = "1453" + System.currentTimeMillis();

    //@Test
    public void assertThatQuickTellerIsNotNull() throws Exception {
        String clientId = "IKIA648C2081347401A269089E4DE1A5BA59B2731410";
        String secret = "6hozIRszPDvG91ZwcqaQmf7agVJb52oll3H/PZ3N+j8=";
        String terminalId = "3DMO0001";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        CustomerValidation customerValidationRequest = new CustomerValidation();

        List<Customers> customersList = new ArrayList<Customers>();
        Customers customers = new Customers();
        customers.setCustomerId("0000000001");
        customers.setPaymentCode("10401");

        customersList.add(customers);

        customerValidationRequest.setCustomers(customersList);

        Assert.assertNotNull(quickTellerSVA.customerValidation(customerValidationRequest));
    }

    //@Test
    public void assertThatPaymentAdviseIsNotNull() throws Exception {
        String clientId = "IKIAE4669CD887A0AECF3434EBD829617582B4267FAC";
        String secret = "0n8A31zauWbDHGIijRKbUWij/1YFYNwjSbGI/y4hePU=";
        String terminalId = "3DMO0001";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        BillPaymentAdvise billPaymentAdvise = new BillPaymentAdvise();
        billPaymentAdvise.setAmount("1460000");
        billPaymentAdvise.setCustomerEmail("joelezeu@gmail.com");
        billPaymentAdvise.setCustomerMobile("2348138249630");
        billPaymentAdvise.setCustomerId("0000000001");
        billPaymentAdvise.setPaymentCode("10401");
        billPaymentAdvise.setRequestReference(reference);

        Assert.assertNotNull(quickTellerSVA.paymentAdvise(billPaymentAdvise));
    }

    //@Test
    public void assertThatPaymentStatusIsNotNull() throws Exception {
        String clientId = "IKIAE4669CD887A0AECF3434EBD829617582B4267FAC";
        String secret = "0n8A31zauWbDHGIijRKbUWij/1YFYNwjSbGI/y4hePU=";
        String terminalId = "3DMO0001";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        String reference = "14531573125719751";
        Assert.assertEquals("90000", quickTellerSVA.billPaymentStatus(reference).getResponseCode());
    }

    //@Test
    public void assertThatPaymentItemsWorks() throws Exception {
        String clientId = "" +
                "";
        String secret = "";
        String terminalId = "";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);
        Assert.assertNotNull(quickTellerSVA.getBouquet("DSTV"));
    }
}

