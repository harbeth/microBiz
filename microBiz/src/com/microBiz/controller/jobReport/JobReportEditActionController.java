package com.microBiz.controller.jobReport;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.meta.JobReportMeta;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.service.JobService;


public class JobReportEditActionController extends RoleBasedSetJobsCommonController {

    private JobService jobService;
    //private ProductService productService;
    private JobReportMeta jrMeta;
    
    public JobReportEditActionController(){
        super();
        jobService = new JobService();
        //productService  = new ProductService();
        jrMeta = new JobReportMeta();
    }
    
    //save job report, jobmaterialreport, joblaborreport
    @Override
    public Navigation run() throws Exception {
       
        JobReport jr = jobService.getJobReport(asKey(jrMeta.key));

        
        BeanUtil.copy(request,jr);
        jr.setWorkingDate();

        jobService.saveJobReport(jr);

        List<JobMaterialReport> jmrList = jr.getJobMaterialReportListRef().getModelList();
        String[] qtys = paramValues("qty");
        String[] prdRatioKeys = paramValues("prdRatioKey");
        
        for(int i=0; i<jmrList.size();i++){
            JobMaterialReport jmr = jmrList.get(i);
            if(!prdRatioKeys[i].equals("-1")){
                PrdRatio prdRatio = productService.getPrdRatio(Datastore.stringToKey(prdRatioKeys[i]));
                jmr.setRatioDesc(prdRatio.getDesc());
                jmr.setRatioRate(prdRatio.getRatio());
            }
  
            jmr.setQty(Double.valueOf(qtys[i]));
           
            
        }
        jobService.saveJobMaterialReports(jmrList);

        setJobsForReportByRole();
        return forward("jobs-to-report.jsp");
    }
}
