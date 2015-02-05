package com.microBiz.model;
import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;


@Model(kind = "job")
public class Job implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    
    @Attribute(persistent = false)
    private String startingDateStr;
    
    private Date startingDate;
    
    private String arrivalTime;
    private String note;
    
    // ongoing, complete, canceled
    private String status;
    // only for display
    @Attribute(persistent = false)
    private String invoiceKey;
    // many to one
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);
    
    private ModelRef<MiUser> installerRef = new ModelRef<MiUser>(MiUser.class);
  
    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }
    

    //display date string from date
    public String getStartingDateStr() {
        return MicroBizUtil.parseDateToStr(startingDate);
    }



    public void setStartingDateStr(String startingDateStr) {
        this.startingDateStr = startingDateStr;
    }



    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }
    //set date from dateStr
    public void setStartingDate() {
        this.startingDate = MicroBizUtil.parseStrToDate(startingDateStr);
    }



    public ModelRef<MiUser> getInstallerRef() {
        return installerRef;
    }




    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
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
        Job other = (Job) obj;
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
