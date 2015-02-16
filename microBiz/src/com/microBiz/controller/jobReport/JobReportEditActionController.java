package com.microBiz.controller.jobReport;

import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.JobReportMeta;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.service.JobService;
import com.microBiz.service.ProductService;


public class JobReportEditActionController extends BaseController {

    private JobService jobService;
    private ProductService productService;
    private JobReportMeta jrMeta;
    
    public JobReportEditActionController(){
        super();
        jobService = new JobService();
        productService  = new ProductService();
        jrMeta = new JobReportMeta();
    }
    
    //save job report, jobmaterialreport, joblaborreport
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        JobReport jr = jobService.getJobReport(asKey(jrMeta.key));

        
        BeanUtil.copy(request,jr);
        

        jr.setReportDate(new Date());
        jobService.saveJobReport(jr);

        List<JobMaterialReport> jmrList = jr.getJobMaterialReportListRef().getModelList();
        String[] qtys = paramValues("qty");
        String[] prdRatioKeys = paramValues("prdRatioKey");
        
        for(int i=0; i<jmrList.size();i++){
            JobMaterialReport jmr = jmrList.get(i);

            Key prdRatioKey = Datastore.stringToKey(prdRatioKeys[i]);
 
            jmr.getPrdRatioRef().setKey(prdRatioKey);
            jmr.setQty(Double.valueOf(qtys[i]));
            jobService.saveJobMaterialReport(jmr);
            
        }

        requestScope("jobs", jobService.getAllUncompleteJobs());
        return forward("jobs-to-report.jsp");
    }
}
