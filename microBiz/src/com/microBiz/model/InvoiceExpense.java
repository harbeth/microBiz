package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.microBiz.MicroBizUtil;


@Model(kind = "invoice_expense")
public class InvoiceExpense extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    private Double expense;
    
    @Attribute(persistent = false)
    private String expenseStr;

    @Attribute(persistent = false)
    private String reportDateStr;
    
    @Attribute(persistent = false)
    private String invoiceKey;

    @Attribute(unindexed = true)
    private String note;
    
    private String canceled;
    
    private String forSalesCommission;
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
    
    public String getExpenseStr() {
        return MicroBizUtil.priceFormat(expense);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getForSalesCommission() {
        return forSalesCommission;
    }

    public void setForSalesCommission(String forSalesCommission) {
        this.forSalesCommission = forSalesCommission;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


 
}
