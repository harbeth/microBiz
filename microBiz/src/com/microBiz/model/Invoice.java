package com.microBiz.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;

@Model
public class Invoice implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    
    private String invoiceNumber;

    @Attribute(persistent = false)
    private String signDateStr;

    private Date signDate;
    
    // sales,(only available to admin, if user is a sales, the this is user)
    private String address;

    @Attribute(unindexed = false)
    private Double deposit;
    
    @Attribute(unindexed = false)
    private String depositPymtMethod;
    
    @Attribute(unindexed = false)
    private String balancePymtMethod;
    
    private String poNumber;
    //use input first, then datePicker
    private Date preferIntlDate;
    
    @Attribute(unindexed = false)
    private String preferIntlDateStr;
    
    @Attribute(unindexed = false)
    private String preferArrivalTime;
    
    @Attribute(unindexed = false)
    private Integer estimatedWorkingHours;
    
    @Attribute(unindexed = false)
    private String note;
    
    private String status;
    private ModelRef<MiUser> salesRef = new ModelRef<MiUser>(MiUser.class);
    private ModelRef<MiUser> creatorRef = new ModelRef<MiUser>(MiUser.class);
    
    // for display
    @Attribute(persistent = false)
    private String customerKey = "-1";
    
    @Attribute(persistent = false)
    private String contactKey = "-1";
    
    private ModelRef<Customer> customerRef = new ModelRef<Customer>(Customer.class);
    private ModelRef<Contact> contactRef = new ModelRef<Contact>(Contact.class);
    private ModelRef<Order> orderRef = new ModelRef<Order>(Order.class);
    
    public ModelRef<Customer> getCustomerRef() {
        return customerRef;
    }

    public ModelRef<Contact> getContactRef() {
        return contactRef;
    }
    
    public ModelRef<MiUser> getSalesRef() {
        return salesRef;
    }

    public ModelRef<MiUser> getCreatorRef() {
        return creatorRef;
    }

    public ModelRef<Order> getOrderRef() {
        return orderRef;
    }

    // many to one
    @Attribute(persistent = false)
    private InverseModelListRef<Payment, Invoice> paymentListRef = new InverseModelListRef<Payment, Invoice>(Payment.class, "invoiceRef", this);
 
    public InverseModelListRef<Payment, Invoice> getPaymentListRef() {
        return paymentListRef;
    }

    @Attribute(persistent = false)
    private InverseModelListRef<Job, Invoice> jobListRef = new InverseModelListRef<Job, Invoice>(Job.class, "invoiceRef", this);

    public InverseModelListRef<Job, Invoice> getJobListRef() {
        return jobListRef;
    }
    
    @Attribute(persistent = false)
    private InverseModelListRef<InvoiceExpense, Invoice> expenseListRef = new InverseModelListRef<InvoiceExpense, Invoice>(InvoiceExpense.class, "invoiceRef", this);

    public InverseModelListRef<InvoiceExpense, Invoice> getExpenseListRef() {
        return expenseListRef;
    }
    
    public String getPreferIntlDateStr() {
        return MicroBizUtil.parseDateToStr(preferIntlDate);

    }

    public void setPreferIntlDateStr(String preferIntlDateStr) {
        this.preferIntlDateStr = preferIntlDateStr;
    }

    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getSignDate() {
        return signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    public void setSignDate() {
        this.signDate = MicroBizUtil.parseStrToDate(signDateStr);
    }


    public Double getDeposit() {
        return deposit;
    }
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    public String getDepositPymtMethod() {
        return depositPymtMethod;
    }
    public void setDepositPymtMethod(String depositPymtMethod) {
        this.depositPymtMethod = depositPymtMethod;
    }
    public String getBalancePymtMethod() {
        return balancePymtMethod;
    }
    public void setBalancePymtMethod(String balancePymtMethod) {
        this.balancePymtMethod = balancePymtMethod;
    }
    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
    public Date getPreferIntlDate() {
        return preferIntlDate;
    }
    public void setPreferIntlDate(Date preferIntlDate) {
        this.preferIntlDate = preferIntlDate;
    }
    public void setPreferIntlDate() {
        this.preferIntlDate = MicroBizUtil.parseStrToDate(preferIntlDateStr);
    }
    public String getPreferArrivalTime() {
        return preferArrivalTime;
    }
    public void setPreferArrivalTime(String preferArrivalTime) {
        this.preferArrivalTime = preferArrivalTime;
    }
    public Integer getEstimatedWorkingHours() {
        return estimatedWorkingHours;
    }
    public void setEstimatedWorkingHours(Integer estimatedWorkingHours) {
        this.estimatedWorkingHours = estimatedWorkingHours;
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
    
     public String getSignDateStr() {
         return MicroBizUtil.parseDateToStr(signDate);
    }

    public void setSignDateStr(String signDateStr) {
        this.signDateStr = signDateStr;
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
        Invoice other = (Invoice) obj;
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