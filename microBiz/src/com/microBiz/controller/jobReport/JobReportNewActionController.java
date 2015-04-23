package com.microBiz.controller.jobReport;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;


public abstract class JobReportNewActionController extends RoleBasedSetJobsCommonController {
    
 
    
    public JobReportNewActionController(){
        super();
        
   
    }
    
    //save job report, jobmaterialreport, 
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        Job job = getJob();
        if(job !=null){
            JobReport jr = new JobReport();
            BeanUtil.copy(request,jr);
            jr.setWorkingDate();
            if(managerApproved()){
                jr.setStatus(MicroBizConst.CODE_STATUS_APPROVED);
            }
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
                    PrdRatio prdRatio = productService.getPrdRatio(Datastore.stringToKey(prdRatioKeys[i]));
                    jmr.setRatioDesc(prdRatio.getDesc());
                    jmr.setRatioRate(prdRatio.getRatio());
                }else{// none or just one prdRatio
                    Product p = productService.get(productKey);
                    List<PrdRatio> prL = p.getPrdRatioList();
                    if(prL!=null && prL.size()>0){
                        
                        jmr.setRatioRate(prL.get(0).getRatio());
                    }else{
                        
                        jmr.setRatioRate(new Double(1));
                    }
                    
                }
                jmr.getProductRef().setKey(productKey);
                jmr.setQty(Double.valueOf(qtys[i]));
                jmr.setCount(new Integer(i));
                jmr.getJobReportRef().setKey(jobKey);
                jmrList.add(jmr);
            }
            jobService.saveJobMaterialReports(jmrList);
        }
        setRequestScope();
        return forward(getForwardJsp());
    }
    
    public abstract Job getJob();
    public abstract String getForwardJsp();
    public abstract void setRequestScope();
    public abstract boolean managerApproved();
}
