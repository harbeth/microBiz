package com.microBiz.controller.manager;

import java.util.List;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.jobReport.JobReportNewActionController;
import com.microBiz.model.Job;
import com.microBiz.model.JobReport;


public class ManagerJobReportEditActionController extends JobReportNewActionController {
    
    public  Job getJob(){
        
        JobReport jr = jobService.getJobReport(asKey("jobReportKey"));
        Job job = jr.getJobRef().getModel();
        jr.setStatus(MicroBizConst.CODE_STATUS_VOID);
        jobService.saveJobReport(jr);
        return job;
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
}
