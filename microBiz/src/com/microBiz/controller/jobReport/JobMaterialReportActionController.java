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
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;
import com.microBiz.service.JobService;


public class JobMaterialReportActionController extends BaseController {

    private JobService jobService;
    private ProductService productService;
    
    public JobMaterialReportActionController(){
        super();
        jobService = new JobService();
        productService  = new ProductService();
    }
    
    //2 steps, 1. save jobreport, jobreport is a child of job
    // 2. add new inventory change and update product inventory, this is in a transaction
    // step 1 and step 2 are not in a transaction
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        Job job = jobService.get(asKey("jobKey"));
        Key productKey = Datastore.stringToKey(asString("productKey"));
        Key prdRatioKey = Datastore.stringToKey(asString("prdRatioKey"));
        Product product  = productService.get(productKey);
        JobMaterialReport jmr = new JobMaterialReport();
        BeanUtil.copy(request,jmr);
        jmr.getProductRef().setModel(product);
        Double changedQty = jmr.getQty()*(-1);
    
        
        if((asString("prdRatioKey"))==null || (asString("prdRatioKey")).equals("")){//there is only no or one prdRation
            if(product.getPrdRatioList()!=null ){// there is only one prdRatio
                PrdRatio pr = product.getPrdRatioList().get(0);
                changedQty = changedQty*pr.getRatio();
                jmr.getPrdRatioRef().setModel(pr);;
            }
                
            // if there is no prdRation, then user entered qty is the changedQty
        }else{// used select the prdRatio
            PrdRatio pr = productService.getPrdRatio(prdRatioKey);
            changedQty = changedQty*pr.getRatio();
            jmr.getPrdRatioRef().setKey(prdRatioKey);
        }
        InventoryChange ic = new InventoryChange();

        Double newQty = product.getCurrentQty() + changedQty;
        ic.setChangeDate(new Date());
        ic.setOriginalQty(product.getCurrentQty());
        ic.setNotes("descrease by invoice " + job.getInvoiceRef().getModel().getInvoiceNumber());
        ic.setNewQty(newQty);
        product.setCurrentQty(newQty);
        jobService.addJobMaterialReport(jmr,job.getKey());
        productService.save(product, ic);
        requestScope("jobs", jobService.getAllUncompleteJobs());
        return forward("jobs-to-report.jsp");
    }
}
