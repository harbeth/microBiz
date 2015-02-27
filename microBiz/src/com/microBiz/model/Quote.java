package com.microBiz.model;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Query.SortDirection;
import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;


@Model
public class Quote extends MiCreatorBaseModel {
    
    private static final long serialVersionUID = 1L;

    private String address;


    // open failed won
    private Integer status;
    // version count
    private Integer count;
    
    // for display
    @Attribute(persistent = false)
    private String customerKey = "-1";
    
    @Attribute(persistent = false)
    private String customerName = "";
    
    @Attribute(persistent = false)
    private String contactKey = "-1";
    
    @Attribute(unindexed = true)
    private String note;
    
    //sort by createAt descending
    @Attribute(persistent = false)
    private InverseModelListRef<QuoteOrder, Quote> quoteOrderRef = new InverseModelListRef<QuoteOrder, Quote>(QuoteOrder.class, "quoteRef", this,new Sort("createdAt", SortDirection.DESCENDING));
 
    public InverseModelListRef<QuoteOrder, Quote> getQuoteOrderRef() {
        return quoteOrderRef;
    }
    
    private ModelRef<Customer> customerRef = new ModelRef<Customer>(Customer.class);
    private ModelRef<Contact> contactRef = new ModelRef<Contact>(Contact.class);
    
    public Quote(){
        status = MicroBizConst.CODE_STATUS_OPEN;
    }
    public ModelRef<Customer> getCustomerRef() {
        return customerRef;
    }
    
    public boolean isHasContact() {
        return contactRef.getModel() != null;
    }
    

    public String getCreateDateStr() {
        return MicroBizUtil.parseDateToStr(createdAt);
    }



    public Integer getStatus() {
        return status;
    }
    
    public String getStatusLable() {
        return getLable(status);
    }

    public void setStatus(Integer status) {
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

   
}
