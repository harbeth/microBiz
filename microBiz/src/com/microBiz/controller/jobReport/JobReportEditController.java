package com.microBiz.controller.jobReport;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.service.JobService;
import com.microBiz.service.ProductService;


public abstract class JobReportEditController extends BaseController {

    protected JobService jobService;
    protected ProductService productService;
    
    public JobReportEditController(){
        super();
        jobService = new JobService();
        productService  = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
       

        JobReport jr = jobService.getJobReport(asKey("jobReportKey"));
        Job job = jr.getJobRef().getModel();
        List<Product> prds = new ArrayList<Product>();
        List<JobMaterialReport> jmrList = jr.getJobMaterialReportListRef().getModelList();
        String[] prdRatioKey = new String[jmrList.size()];

        String[] qty = new String[jmrList.size()];
        
    
        
        for (int a = 0; a< jmrList.size(); a++){
            JobMaterialReport jmr = jmrList.get(a);
            Product prd = productService.get(jmr.getProductRef().getKey());
            qty[a]=jmr.getQty().toString();
            if(jmr.getRatioDesc()!=null && prd.getShowRatio()){
                PrdRatio prdR = productService.getPrdRatioByPrdAndDesc(prd.getKey(), jmr.getRatioDesc());
                prdRatioKey[a] = Datastore.keyToString(prdR.getKey());
            }else{
                prdRatioKey[a] = "-1";
            }
            //System.out.println("prdR key is " + prdRatioKey[a]);
            prds.add(prd);
        }
        requestScope("qty", qty);
        requestScope("prdRatioKey", prdRatioKey);
        requestScope("prds", prds);

        BeanUtil.copy(jr,request);


        requestScope("job", job);
        setRequestScope(jr);
        return forward(getReturnedJsp());
    }

    public abstract String getReturnedJsp();
    public abstract void setRequestScope(JobReport jr);

}
