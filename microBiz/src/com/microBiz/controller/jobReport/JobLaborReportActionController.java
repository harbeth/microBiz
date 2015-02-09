package com.microBiz.controller.jobReport;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Job;
import com.microBiz.model.JobLaborReport;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;
import com.microBiz.service.JobService;


public class JobLaborReportActionController extends BaseController {

    private JobService jobService;
    private ProductService productService;    
    public JobLaborReportActionController(){
        super();
        jobService = new JobService();

    }
    
    //2 steps, 1. save jobreport, jobreport is a child of job

    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        Key jobKey = asKey("jobKey");

        JobLaborReport jbr = new JobLaborReport();
        BeanUtil.copy(request,jbr);

        jobService.saveJobLaborReport(jbr,jobKey);

        requestScope("jobs", jobService.getAllUncompleteJobs());
        return forward("jobs-to-report.jsp");
    }
}
