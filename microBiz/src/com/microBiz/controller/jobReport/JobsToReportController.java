package com.microBiz.controller.jobReport;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;


public class JobsToReportController extends RoleBasedSetJobsCommonController {

  
    
    public JobsToReportController(){
        super();
    
    }
    
    @Override
    public Navigation run() throws Exception {
        setJobsForReportByRole();
        return forward("job-report-wrapper.jsp");
    }
}
