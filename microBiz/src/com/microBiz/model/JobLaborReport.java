package com.microBiz.model;
import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;


@Model(kind = "job_labor_report")
public class JobLaborReport implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
      
    private String date;
    private Integer travelHours;
    private Integer workingHours;
    private String materialReport;
    private String otherExpense;
    private Double amount;
    private String note;
    // many to one
    private ModelRef<Job> jobRef = new ModelRef<Job>(Job.class);
  
    public ModelRef<Job> getJobRef() {
        return jobRef;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTravelHours() {
        return travelHours;
    }

    public void setTravelHours(Integer travelHours) {
        this.travelHours = travelHours;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public String getMaterialReport() {
        return materialReport;
    }

    public void setMaterialReport(String materialReport) {
        this.materialReport = materialReport;
    }

    public String getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(String otherExpense) {
        this.otherExpense = otherExpense;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        JobLaborReport other = (JobLaborReport) obj;
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
