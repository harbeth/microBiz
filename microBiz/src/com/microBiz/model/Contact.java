package com.microBiz.model;
import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;


@Model(kind = "contact")
public class Contact extends MiCreatorBaseModel {
    
 
    private String name;
    
    @Attribute(unindexed = true)
    private String type; //user entered by himself
    
    private String phone;
    
    private String altPhone;
        
    private String email;
    
    @Attribute(unindexed = true)
    private String notes;
    
    // only for display
    @Attribute(persistent = false)
    private boolean selected;
    @Attribute(persistent = false)
    private String customerKey;
    
    private ModelRef<Customer> customerRef = new ModelRef<Customer>(Customer.class);
  
    public ModelRef<Customer> getCustomerRef() {
        return customerRef;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
