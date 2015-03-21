package com.microBiz.controller.jobReport;

import org.slim3.controller.Navigation;


public class JobsToReportController extends RoleBasedSetJobsCommonController {

    
    @Override
    public Navigation run() throws Exception {
        setJobsForReportByRole();
        return forward("job-report-wrapper.jsp");
    }
}
