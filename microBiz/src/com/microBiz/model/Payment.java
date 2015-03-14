package com.microBiz.model;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;

@Model
public class Payment extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double amount;
    
    @Attribute(persistent = false)
    private String amoutStr;

    private Integer method;
    
    
    private String note;

    @Attribute(persistent = false)
    private String enterDateStr;
 
    // only for display
    @Attribute(persistent = false)
    private String invoiceKey;
    
    private String canceled;
    
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
    
    public Integer getMethod() {
        return method;
    }
    
    public String getMethodLable() {
        return getLable(method);
    }
    
    public void setMethod(Integer method) {
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
    
    public String getAmountStr() {
        return MicroBizUtil.priceFormat(amount);
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
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
