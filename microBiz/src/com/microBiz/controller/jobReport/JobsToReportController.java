package com.microBiz.controller.jobReport;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Job;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;


public class JobsToReportController extends BaseController {

    private JobService jobService;
    
    public JobsToReportController(){
        super();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        List<Job> jobs = jobService.getAllUncompleteJobs();
        requestScope("jobs", jobs);
        return forward("job-report-wrapper.jsp");
    }
}
