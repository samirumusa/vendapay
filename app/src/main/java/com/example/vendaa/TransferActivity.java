package com.example.vendaa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vendaa.service.QuickTellerSVA;
import com.example.vendaa.service.impl.QuickTellerSVAImpl;

import java.util.ArrayList;
import java.util.List;


public class TransferActivity extends AppCompatActivity {
    String reference = "1453" + System.currentTimeMillis();
    //@Test
    String clientId = "IKIA18F710BD302F677E0C93F5F1214580287F544F88";
    String secret = "5EDC4AB420533336AA0FE68084AA76BEE6B8E4C2";
    String terminalId = "3DMO0001";
    QuickTellerSVAImpl quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);
    TextView Customervalidation, name, email, phone, password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_activity);



        Button buttonReg;
        EditText editEmail;


        buttonReg = (Button) findViewById(R.id.butttonReg);
        editEmail = (EditText) findViewById(R.id.edittEmail);
        Customervalidation =(TextView) findViewById(R.id.textView);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            private String reference = "1453" + System.currentTimeMillis();

            @Override
            public void onClick(View v) {



                CustomerValidation customerValidationRequest = new CustomerValidation();
                //create a list call customerlist
                // if we call customerlist in the future it shall have all the transaction history
                List<Customers> customersList = new ArrayList<Customers>();
                // this variable has all the field in Customers.java
                Customers customers = new Customers();
                //among the field there are field call customerID and paymentcode
                customers.setCustomerId("0000000001");
                customers.setPaymentCode("10401");
                //add the customer ID and the paymentcode (which is constant id for a payment feature)
                // to our list
                customersList.add(customers);
                // we add the customer to our validation list to tell the company about the payment
                customerValidationRequest.setCustomers(customersList);
                //the aim of the validation class is to get and set the customer for verification(getter and setter)
                // we push the validation request to the company
                //we then set the customer to quickteller server for validation
                try {
                    quickTellerSVA.customerValidation(customerValidationRequest);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                BillPaymentAdvise billPaymentAdvise = new BillPaymentAdvise();
                billPaymentAdvise.setAmount("1460000");
                billPaymentAdvise.setCustomerEmail("joelezeu@gmail.com");
                billPaymentAdvise.setCustomerMobile("2348138249630");
                billPaymentAdvise.setCustomerId("0000000001");
                billPaymentAdvise.setPaymentCode("10401");
                billPaymentAdvise.setRequestReference(reference);

                try {
                    quickTellerSVA.paymentAdvise(billPaymentAdvise);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String referenceI = "14531573125719751";

                try {

                    Customervalidation.setText(quickTellerSVA.billPaymentStatus(reference).getResponseCode());
                    String mutual = quickTellerSVA.billPaymentStatus(referenceI).getResponseCode();
                   // Toast.makeText(TransferActivity.this,mutual,Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                // then a door would be open for us to make the payment
                //Customervalidation.setText(gettext);
                // Customervalidation.setText(quickTellerSVA.customerValidation(customerValidationRequest).toString());

            }





        });
    }



    public void assertThatQuickTellerIsNotNull() throws Exception {
        String clientId = "IKIA18F710BD302F677E0C93F5F1214580287F544F88";
        String secret = "5EDC4AB420533336AA0FE68084AA76BEE6B8E4C2";
        String terminalId = "3DMO0001";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        CustomerValidation customerValidationRequest = new CustomerValidation();
        //create a list call customerlist
        // if we call customerlist in the future it shall have all the transaction history
        List<Customers> customersList = new ArrayList<Customers>();
        // this variable has all the field in Customers.java
        Customers customers = new Customers();
        //among the field there are field call customerID and paymentcode
        customers.setCustomerId("0000000001");
        customers.setPaymentCode("10401");
        //add the customer ID and the paymentcode (which is constant id for a payment feature)
        // to our list
        customersList.add(customers);
        // we add the customer to our validation list to tell the company about the payment
        customerValidationRequest.setCustomers(customersList);
        //the aim of the validation class is to get and set the customer for verification(getter and setter)
        // we push the validation request to the company
        //we then set the customer to quickteller server for validation
        quickTellerSVA.customerValidation(customerValidationRequest);



        // then a door would be open for us to make the payment
        //Customervalidation.setText(gettext);
       // Customervalidation.setText(quickTellerSVA.customerValidation(customerValidationRequest).toString());

    }

    //@Test
    public void assertThatPaymentAdviseIsNotNull() throws Exception {
        String clientId = "IKIA18F710BD302F677E0C93F5F1214580287F544F88";
        String secret = "5EDC4AB420533336AA0FE68084AA76BEE6B8E4C2";
        String terminalId = "3DMO0001";

        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        BillPaymentAdvise billPaymentAdvise = new BillPaymentAdvise();
        billPaymentAdvise.setAmount("1460000");
        billPaymentAdvise.setCustomerEmail("joelezeu@gmail.com");
        billPaymentAdvise.setCustomerMobile("2348138249630");
        billPaymentAdvise.setCustomerId("0000000001");
        billPaymentAdvise.setPaymentCode("10401");
        billPaymentAdvise.setRequestReference(reference);

        //Assert.assertNotNull(quickTellerSVA.paymentAdvise(billPaymentAdvise));
        Customervalidation.setText(quickTellerSVA.paymentAdvise(billPaymentAdvise).toString());


    }

    //@Test
    public void assertThatPaymentStatusIsNotNull() throws Exception {
        String clientId = "IKIA18F710BD302F677E0C93F5F1214580287F544F88";
        String secret = "5EDC4AB420533336AA0FE68084AA76BEE6B8E4C2";
        String terminalId = "3DMO0001";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);

        String reference = "14531573125719751";
        Customervalidation.setText(quickTellerSVA.billPaymentStatus(reference).getResponseCode());


    }

    //@Test
    public void assertThatPaymentItemsWorks() throws Exception {
        String clientId = "" +
                "";
        String secret = "";
        String terminalId = "";
        QuickTellerSVA quickTellerSVA = new QuickTellerSVAImpl(clientId, secret, terminalId);
        Customervalidation.setText(quickTellerSVA.getBouquet("DSTV").toString());

    }
}












