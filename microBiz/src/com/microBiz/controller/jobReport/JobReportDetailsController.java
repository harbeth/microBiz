package com.microBiz.controller.jobReport;

import java.util.List;
import com.google.appengine.api.datastore.Key;
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


public class JobReportDetailsController extends BaseController {

    private JobService jobService;
    
    public JobReportDetailsController(){
        super();
        jobService = new JobService();

    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details

        
        Key jobKey = asKey("jobKey");
        Job job = jobService.get(jobKey);
        List<JobMaterialReport> jmrList = jobService.getJobMaterialReports(jobKey);
        List<JobLaborReport> jlrList = jobService.getJobLaborReports(jobKey);
        requestScope("job", job);
        if(jmrList!=null && jmrList.size()>0){
            requestScope("jobMaterialReports", jmrList);
        }
        if(jlrList!=null && jlrList.size()>0){
            requestScope("jobLaborReports", jlrList);
        }
        return forward("job-report-details.jsp");
    }


}
