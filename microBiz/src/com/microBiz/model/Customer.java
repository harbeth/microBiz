package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import com.microBiz.MicroBizConst;


@Model(kind = "ctm")
public class Customer extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    @Attribute(unindexed = true)
    // residential or commercial
    private Integer type;
    
    private String name;
    
    private String phone;
    
    private String pymtTerm;
    
    private String altPhone;
    
    private String address;
    
    private String email;
    
    @Attribute(persistent = false)
    // for validation on FE, commercial cannot change to residential
    private Integer oldCustomerType;
    
    @Attribute(unindexed = true)
    private String rating;
    
    @Attribute(unindexed = true)
    private String notes;
    
    @Attribute(unindexed = true)
    private String active;
    
    @Attribute(persistent = false)
    private InverseModelListRef<Contact, Customer> contactListRef = new InverseModelListRef<Contact, Customer>(Contact.class, "customerRef", this);

 
    public InverseModelListRef<Contact, Customer> getContactListRef() {
        return contactListRef;
    }
    
    @Attribute(persistent = false)
    private InverseModelListRef<Quote, Customer> quoteListRef = new InverseModelListRef<Quote, Customer>(Quote.class, "customerRef", this);

 
    public InverseModelListRef<Quote, Customer> getQuoteListRef() {
        return quoteListRef;
    }
    
    @Attribute(persistent = false)
    private InverseModelListRef<Invoice, Customer> invoiceListRef = new InverseModelListRef<Invoice, Customer>(Invoice.class, "customerRef", this);

 
    public InverseModelListRef<Invoice, Customer> getInvoiceListRef() {
        return invoiceListRef;
    }
    
    @Attribute(persistent = false)
    // only for display
    private boolean selected;
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean isCommercial() {
        return Integer.valueOf(MicroBizConst.CODE_CUSTOMER_TYPE_COMMERCIAL) == type;
    }
 
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
    public Integer getOldCustomerType() {
        return oldCustomerType;
    }

    public void setOldCustomerType(Integer oldCustomerType) {
        this.oldCustomerType = oldCustomerType;
    }

    public String getPymtTerm() {
        return pymtTerm;
    }

    public void setPymtTerm(String pymtTerm) {
        this.pymtTerm = pymtTerm;
    }

  
    
}
