package com.microBiz.controller.jobReport;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.service.JobService;


public abstract class JobReportNewActionController extends BaseController {

    protected JobService jobService;
    //private ProductService productService;
    
    public JobReportNewActionController(){
        super();
        jobService = new JobService();
        //productService  = new ProductService();
    }
    
    //save job report, jobmaterialreport, 
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        Job job = getJob();
 
        
        JobReport jr = new JobReport();
       
        BeanUtil.copy(request,jr);
        
        
        
  
        jr.getJobRef().setModel(job);
        Key jobKey = jobService.saveJobReport(jr);
        
        List<String> prdKeys = job.getUsePrdKeys();
        List<JobMaterialReport> jmrList = new ArrayList<JobMaterialReport>();
        String[] qtys = paramValues("qty");
        String[] prdRatioKeys = paramValues("prdRatioKey");
       
        for(int i=0; i<prdKeys.size();i++){
            JobMaterialReport jmr = new JobMaterialReport();
            Key productKey = Datastore.stringToKey(prdKeys.get(i));
            if(!prdRatioKeys[i].equals("-1")){
                Key prdRatioKey = Datastore.stringToKey(prdRatioKeys[i]);
                jmr.getPrdRatioRef().setKey(prdRatioKey);
            }
            jmr.getProductRef().setKey(productKey);
            jmr.setQty(Double.valueOf(qtys[i]));
            jmr.setCount(new Integer(i));
            jmr.getJobReportRef().setKey(jobKey);
            jmrList.add(jmr);
            
            
        }
        jobService.saveJobMaterialReports(jmrList);
        setRequestScope();
        return forward(getForwardJsp());
    }
    
    public abstract Job getJob();
    public abstract String getForwardJsp();
    public abstract void setRequestScope();
}
