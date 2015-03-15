package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.JobService;

// no use any more, remove later
public class UpdateUnApprovedJobReportSectionController extends BaseController {

    private JobService jobService;
    
    public UpdateUnApprovedJobReportSectionController(){
        super();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
       
        int newJobReportCount = jobService.getNewJobReports().size();
        requestScope("newJobReportCount",newJobReportCount);
        return forward("section-unapprove-job.jsp");
    }
}
