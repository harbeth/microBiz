package com.microBiz.controller.jobReport;

import com.microBiz.model.Job;


public class InstallerJobReportNewActionController extends JobReportNewActionController {


    
    public  Job getJob(){
        return jobService.get(asKey("jobKey"));
    }

    @Override
    public String getForwardJsp() {
        return "jobs-to-report.jsp";
    }

    @Override
    public void setRequestScope(){
        setJobsForReportByRole();
        
    }

    @Override
    public boolean managerApproved() {
        
        return false;
    }
}
