package com.microBiz.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Query.SortDirection;
import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;
import com.microBiz.PropertyHelper;

@Model(kind = "inv")
public class Invoice extends MiCreatorBaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceNumber;

    @Attribute(persistent = false)
    private String signDateStr;

    private Date signDate;

    private String address;

    @Attribute(unindexed = true)
    private Double deposit;

    @Attribute(unindexed = true)
    private Integer depositPymtMethod;

    @Attribute(unindexed = true)
    private Integer balancePymtMethod;

    @Attribute(unindexed = true)
    private String poNumber;

    private Date preferIntlDate;

    @Attribute(persistent = false)
    private String preferIntlDateStr;

    @Attribute(unindexed = true)
    private String preferArrivalTime;

    @Attribute(unindexed = true)
    private Double estimatedWorkingHours;

    @Attribute(unindexed = true)
    private String note;
    
    @Attribute(unindexed = true)
    private String noteToCx;

    private Integer status;
    
    private Date statusChangeDate;

    private String sales;
    
    private Boolean paidOff;
    
    private Boolean salesPaid;

    // for display
    @Attribute(persistent = false)
    private String customerKey = "-1";

    @Attribute(persistent = false)
    private String customerName = "";

    @Attribute(persistent = false)
    private String contactKey = "-1";

    private ModelRef<Customer> customerRef = new ModelRef<Customer>(
        Customer.class);

    @Attribute(unindexed = true)
    private ModelRef<Contact> contactRef = new ModelRef<Contact>(Contact.class);

    @Attribute(unindexed = true)
    private ModelRef<Order> orderRef = new ModelRef<Order>(Order.class);

    @Attribute(unindexed = true)
    private ModelRef<InvoiceReport> invoiceReportRef =
        new ModelRef<InvoiceReport>(InvoiceReport.class);

    public Invoice() {
        status = MicroBizConst.CODE_STATUS_OPEN;
    }

    public boolean isHasContact() {
        return contactRef.getModel() != null;
    }

    public ModelRef<Customer> getCustomerRef() {
        return customerRef;
    }

    public ModelRef<Contact> getContactRef() {
        return contactRef;
    }

    public ModelRef<Order> getOrderRef() {
        return orderRef;
    }

    // many to one
    @Attribute(persistent = false)
    private InverseModelListRef<Payment, Invoice> paymentListRef =
        new InverseModelListRef<Payment, Invoice>(
            Payment.class,
            "invoiceRef",
            this,
            new Sort("createdAt", SortDirection.DESCENDING));

    public InverseModelListRef<Payment, Invoice> getPaymentListRef() {
        return paymentListRef;
    }

    @Attribute(persistent = false)
    private InverseModelListRef<Job, Invoice> jobListRef =
        new InverseModelListRef<Job, Invoice>(Job.class, "invoiceRef", this,new Sort("createdAt", SortDirection.DESCENDING));

    public InverseModelListRef<Job, Invoice> getJobListRef() {
        return jobListRef;
    }

    @Attribute(persistent = false)
    private InverseModelListRef<InvoiceExpense, Invoice> expenseListRef =
        new InverseModelListRef<InvoiceExpense, Invoice>(
            InvoiceExpense.class,
            "invoiceRef",
            this,
            new Sort("createdAt", SortDirection.DESCENDING));

    public InverseModelListRef<InvoiceExpense, Invoice> getExpenseListRef() {
        return expenseListRef;
    }

    public ModelRef<InvoiceReport> getInvoiceReportRef() {
        return invoiceReportRef;
    }
    
    public boolean getCloseCanceled(){
        if(status.intValue()==MicroBizConst.CODE_STATUS_OPEN.intValue()){
            return false;
        }else{
            return true;
        }
        
    }
    
    public boolean getNotCloseCancelable(){
        if(key==null){
            return true;
        }
        
        if(invoiceReportRef.getModel().getOnGoingJobCount()== 0){
            return false;
        }else{
            return true;
        }
    }
    
    //as  one installer can only has one open job for a invoice, so if an invoice has the same number of open job as the number of installer
    //meaning all installer have open job for the invoice, don't show assign job link for the invoice
    public boolean getShowAssignJobLink(){
        if(key == null){
            return true;
        }
        if(status.intValue()==MicroBizConst.CODE_STATUS_CANCELED.intValue()){
            return false;
        }
        
        /*
        int installerCount = PropertyHelper.getInstance().getInstallers().size();
        
        if( installerCount >  invoiceReportRef.getModel().getOnGoingJobCount()){
            return true;
        }else{
            return false;
        }
        */
        return true;
    }
    
    public Double getSalesCommission(){
        List<InvoiceExpense> ie = expenseListRef.getModelList();
        Double result = new Double(0);
        Iterator<InvoiceExpense> i = ie.iterator();
        while (i.hasNext()){
            InvoiceExpense e = i.next();
            if(e.getForSalesCommission().equals("on")){
                result = result + e.getExpense();
            }
        }
        
        return result;
        
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

    public Integer getDepositPymtMethod() {
        return depositPymtMethod;
    }
    
    public String getDepositPymtMethodLable() {
        return getLable(depositPymtMethod);
    }

    public void setDepositPymtMethod(Integer depositPymtMethod) {
        this.depositPymtMethod = depositPymtMethod;
    }

    public Integer getBalancePymtMethod() {
        return balancePymtMethod;
    }
    
    public String getBalancePymtMethodLable() {
        return getLable(balancePymtMethod);
    }

    public void setBalancePymtMethod(Integer balancePymtMethod) {
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

    public Double getEstimatedWorkingHours() {
        return estimatedWorkingHours;
    }

    public void setEstimatedWorkingHours(Double estimatedWorkingHours) {
        this.estimatedWorkingHours = estimatedWorkingHours;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getStatusLable() {
        return getLable(status);
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }
    
    public String getStatusChangeDateStr() {
        return MicroBizUtil.parseDateToStr(statusChangeDate);
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
    
    public Boolean getPaidOff() {
        return paidOff;
    }

    public void setPaidOff(Boolean paidOff) {
        this.paidOff = paidOff;
    }

    public Boolean getSalesPaid() {
        return salesPaid;
    }

    public void setSalesPaid(Boolean salesPaid) {
        this.salesPaid = salesPaid;
    }

    public String getNoteToCx() {
        return noteToCx;
    }

    public void setNoteToCx(String noteToCx) {
        this.noteToCx = noteToCx;
    }
    
    


}