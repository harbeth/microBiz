package com.microBiz.controller.manager;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.JobReport;
import com.microBiz.service.JobService;

// no use any more, remove later
public class DashboardController extends BaseController {

    private JobService jobService;
    
    public DashboardController(){
        super();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        List<JobReport> jobReportsToApprove = jobService.getNewJobReports();
        requestScope("jobReports", jobReportsToApprove);
        return forward("dashboard-wrapper.jsp");
    }
}
