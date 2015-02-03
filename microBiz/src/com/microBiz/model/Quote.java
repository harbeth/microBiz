package com.microBiz.model;
import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;


@Model
public class Quote implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    
    private String address;
    private String createDate;
    private String creator;
    // open failed won
    private String status;
    // version count
    private Integer count;
    
    // for display
    @Attribute(persistent = false)
    private String customerKey = "-1";
    
    @Attribute(persistent = false)
    private String contactKey = "-1";
    
    @Attribute(persistent = false)
    // only for create, just one
    private QuoteVersion quoteVersion;
    
    @Attribute(unindexed = true)
    private String note;
    
    // for quote version, new
    @Attribute(persistent = false)
    private Double taxRate;
    @Attribute(persistent = false)
    private Double discount;
    @Attribute(persistent = false)
    private Double total;
    
    //sort by createAt descending
    @Attribute(persistent = false)
    private InverseModelListRef<QuoteVersion, Quote> quoteVersionsRef = new InverseModelListRef<QuoteVersion, Quote>(QuoteVersion.class, "quoteRef", this,new Sort("createAt", SortDirection.DESCENDING));
 
    public InverseModelListRef<QuoteVersion, Quote> getQuoteVersionsRef() {
        return quoteVersionsRef;
    }
    
    private ModelRef<Customer> customerRef = new ModelRef<Customer>(Customer.class);
    private ModelRef<Contact> contactRef = new ModelRef<Contact>(Contact.class);
    
    public ModelRef<Customer> getCustomerRef() {
        return customerRef;
    }
    
    public boolean isHasContact() {
        return contactRef.getModel() != null;
    }
    
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ModelRef<Contact> getContactRef() {
        return contactRef;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public QuoteVersion getQuoteVersion() {
        return quoteVersion;
    }

    public void setQuoteVersion(QuoteVersion quoteVersion) {
        this.quoteVersion = quoteVersion;
    }
    
    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getContactKey() {
        return contactKey;
    }

    public void setContactKey(String contactKey) {
        this.contactKey = contactKey;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
        Quote other = (Quote) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
