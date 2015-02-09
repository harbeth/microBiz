package com.microBiz.controller.jobReport;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.model.JobLaborReport;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;
import com.microBiz.service.JobService;


public class JobLaborReportController extends BaseController {

    private JobService jobService;

    
    public JobLaborReportController(){
        super();
        jobService = new JobService();

    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details

        
        Job job = jobService.get(asKey("jobKey"));
        JobLaborReport jbr = new JobLaborReport();
        BeanUtil.copy(jbr,request);


        requestScope("job", job);
        return forward("job-labor-report.jsp");
    }


}
