package com.microBiz.controller.manager;

import java.util.Iterator;
import java.util.List;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.jobReport.JobReportNewActionController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;


public class ManagerJobReportEditActionController extends JobReportNewActionController {
    
    public  Job getJob(){
        String action = asString("action");
        JobReport jr = jobService.getJobReport(asKey("jobReportKey"));
        if(action.equals("approve")){
            jr.setStatus(MicroBizConst.CODE_STATUS_APPROVED);
            
        }else{
            jr.setStatus(MicroBizConst.CODE_STATUS_VOID);      
        }
        jobService.saveJobReport(jr);
        
        if(action.equals("approveWithChange")){
            return jr.getJobRef().getModel();
        
        }else{
           return null;
        }
 
    }
    

    @Override
    public String getForwardJsp() {
        // TODO Auto-generated method stub
        return "unApprovedJobReports.jsp";
    }

    @Override
    public void setRequestScope() {
        List<JobReport> jobReportsToApprove = jobService.getNewJobReports();
        requestScope("jobReports", jobReportsToApprove);

        
    }

    @Override
    public boolean managerApproved() {
        String action = asString("action");
        if(action.equals("void")){
            return false;
        }else{
            return true;
        }
    }
}
