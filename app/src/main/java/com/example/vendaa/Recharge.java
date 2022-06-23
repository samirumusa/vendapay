package com.example.vendaa;

public class Recharge {
    //    “biller”: “ZainMTU”,
//            “customerId1”: “08090673520”,
//            “customerId2”: null,
//            “paymentTypeName”: “Zain MTU 50”,
//            “paymentTypeCode”: “01”,
//            “billerId”: “901”
    private String biller;
    private String customerId1;
    private String customerId2;
    private String paymentTypeName;
    private String paymentTypeCode;
    private String billerId;

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getCustomerId1() {
        return customerId1;
    }

    public void setCustomerId1(String customerId1) {
        this.customerId1 = customerId1;
    }

    public String getCustomerId2() {
        return customerId2;
    }

    public void setCustomerId2(String customerId2) {
        this.customerId2 = customerId2;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }
}
