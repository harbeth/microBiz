package com.microBiz.model;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;

@Model(kind = "job")
public class Job extends MiCreatorBaseModel {

    private static final long serialVersionUID = 1L;

    @Attribute(persistent = false)
    private String startingDateStr;

    private Date startingDate;

    @Attribute(unindexed = true)
    private String arrivalTime;

    @Attribute(unindexed = true)
    private String note;

    @Attribute(unindexed = true)
    private List<String> usePrdKeys;

    // ongoing, complete, canceled
    private Integer status;
    // only for display
    @Attribute(persistent = false)
    private String invoiceKey;

    // many to one
    private ModelRef<Invoice> invoiceRef = new ModelRef<Invoice>(Invoice.class);

    private ModelRef<MiUser> installerRef = new ModelRef<MiUser>(MiUser.class);

    @Attribute(persistent = false)
    private InverseModelListRef<JobReport, Job> jobReportListRef =
        new InverseModelListRef<JobReport, Job>(JobReport.class, "jobRef", this);

    public Job() {
        status = MicroBizConst.CODE_STATUS_OPEN;
    }

    public InverseModelListRef<JobReport, Job> getJobReportListRef() {
        return jobReportListRef;
    }

    public ModelRef<Invoice> getInvoiceRef() {
        return invoiceRef;
    }

    // display date string from date
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

    // set date from dateStr
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

    public Integer getStatus() {
        return status;
    }

    public String getStatusLable() {
        return getLable(status);
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }

    public void setInvoiceKey(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public List<String> getUsePrdKeys() {
        return usePrdKeys;
    }

    public void setUsePrdKeys(List<String> usePrdKeys) {
        this.usePrdKeys = usePrdKeys;
    }

}
