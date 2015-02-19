package com.microBiz.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;

@Model
public class Payment extends MiCreatorBaseModel {
    
 
    
    @Attribute(unindexed = true)
    private Double amount;

    private String method;
    
    
    private String note;

    @Attribute(persistent = false)
    private String enterDateStr;
 
    // only for display
    @Attribute(persistent = false)
    private String invoiceKey;
    
    // many to one for Invoice
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);
    
    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }
    
    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }
   
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
 

    public String getEnterDateStr() {
        return MicroBizUtil.parseDateToStr(createdAt);

    }
    
 

    public void setEnterDateStr(String enterDateStr) {
        this.enterDateStr = enterDateStr;
    }

  
    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }
}
