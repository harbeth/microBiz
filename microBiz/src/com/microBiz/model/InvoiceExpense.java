package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.microBiz.MicroBizUtil;


@Model(kind = "invoice_expense")
public class InvoiceExpense extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    private Double expense;

    @Attribute(persistent = false)
    private String reportDateStr;
    
    @Attribute(persistent = false)
    private String invoiceKey;

    @Attribute(unindexed = true)
    private String note;
    // many to one
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }
    
    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

 

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }
    
    //display date string from date
    public String getReportDateStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

 
}
