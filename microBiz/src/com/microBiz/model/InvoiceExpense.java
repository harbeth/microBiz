package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;


@Model(kind = "invoice_expense")
public class InvoiceExpense implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private Date reportDate;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }
    
    //display date string from date
    public String getReportDateStr() {
        return MicroBizUtil.parseDateToStr(reportDate);
    }
    
    //set date from dateStr
    public void setReportDate() {
        this.reportDate = MicroBizUtil.parseStrToDate(reportDateStr);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InvoiceExpense other = (InvoiceExpense) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
