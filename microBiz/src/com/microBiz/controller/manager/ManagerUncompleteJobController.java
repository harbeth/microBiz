package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.JobService;

// no use any more, remove later
public class ManagerUncompleteJobController extends BaseController {

    private JobService jobService;
    
    public ManagerUncompleteJobController(){
        super();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        //List<JobReport> jobReportsToApprove = jobService.getNewJobReports();
        //requestScope("jobReports", jobReportsToApprove);
        
        // get all invoice list for now, should get not fully paied invoice list
        requestScope("jobs", jobService.getAllUncompleteJobs());
        return forward("manager-uncomplete-job-list.jsp");
    }
}
