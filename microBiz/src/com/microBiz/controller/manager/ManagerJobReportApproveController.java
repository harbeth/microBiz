package com.microBiz.controller.manager;

import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.JobReport;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.service.JobService;
import com.microBiz.service.ProductService;


public class ManagerJobReportApproveController extends BaseController {

    protected JobService jobService;
    private ProductService productService;
   
    
    public ManagerJobReportApproveController(){
        super();
        jobService = new JobService();
        productService  = new ProductService();
       
    }
    
    @Override
    public Navigation run() throws Exception {
        JobReport jr = jobService.getJobReport(asKey("jobReportKey"));
        jr.setStatus(MicroBizConst.CODE_STATUS_APPROVED);
        jobService.saveJobReport(jr);
        List<JobMaterialReport> jmrs = jr.getJobMaterialReportListRef().getModelList();
        Iterator<JobMaterialReport> i = jmrs.iterator();
       
        while(i.hasNext()){
            JobMaterialReport jmr = i.next();
            Product prd = jmr.getProductRef().getModel();
            Double reportQty = jmr.getQty();
            Double changedQty = null;
            if(jmr.getPrdRatioRef()!=null && jmr.getPrdRatioRef().getModel()!=null){
                PrdRatio pr = jmr.getPrdRatioRef().getModel();
                changedQty = reportQty*pr.getRatio()*(-1);
            }else{
                if(prd.getPrdRatioList()!=null && prd.getPrdRatioList().size()==1){//only one PrdRatio
                    changedQty = reportQty*prd.getPrdRatioList().get(0).getRatio()*(-1);
                }else{
                    changedQty = reportQty*(-1);
                }
            }
            InventoryChange ic = new InventoryChange();
            ic.setChangeQty(changedQty);
            ic.setOriginalQty(prd.getCurrentQty());
            Double newQty = prd.getCurrentQty() + changedQty;
            ic.setNewQty(newQty);
            ic.setNotes("descrease by invoice " + jr.getJobRef().getModel().getInvoiceRef().getModel().getInvoiceNumber());
            prd.setCurrentQty(newQty);
            productService.save(prd, ic);
            
       }
            
   
        
        
        return forward("jobApprovedSuccess.jsp");
    }

}
