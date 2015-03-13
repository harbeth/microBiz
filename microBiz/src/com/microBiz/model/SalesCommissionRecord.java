package com.microBiz.model;

//this is a helper class to display paid sales commission
public class SalesCommissionRecord{
    
    private String invoiceNumber;
    private String note;
    private String amount;
    private String address;
    private String signedDate;
    private String closedDate;
    
    
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSignedDate() {
        return signedDate;
    }
    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }
    public String getClosedDate() {
        return closedDate;
    }
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }
    
    

}
