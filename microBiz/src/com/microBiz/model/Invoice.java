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
public class Invoice extends MiBaseModel {
      
    private String invoiceNumber;

    @Attribute(persistent = false)
    private String signDateStr;

    private Date signDate;
    
    // sales,(only available to admin, if user is a sales, the this is user)
    private String address;

    @Attribute(unindexed = true)
    private Double deposit;
    
    @Attribute(unindexed = true)
    private String depositPymtMethod;
    
    @Attribute(unindexed = true)
    private String balancePymtMethod;
    
    private String poNumber;

    private Date preferIntlDate;
    
    @Attribute(unindexed = true)
    private String preferIntlDateStr;
    
    @Attribute(unindexed = true)
    private String preferArrivalTime;
    
    @Attribute(unindexed = true)
    private Integer estimatedWorkingHours;
    
    @Attribute(unindexed = true)
    private String note;
    
    private String status;
    private ModelRef<MiUser> salesRef = new ModelRef<MiUser>(MiUser.class);
    private ModelRef<MiUser> creatorRef = new ModelRef<MiUser>(MiUser.class);
    
    // for display
    @Attribute(persistent = false)
    private String customerKey = "-1";
    
    @Attribute(persistent = false)
    private String customerName = "";
    
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
    
    public boolean isHasContact() {
        return contactRef.getModel() != null;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

  
}