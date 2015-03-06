package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.JobEditActionCommonController;
import com.microBiz.model.Invoice;


// from AJAX call, on the submit form of jab details form
public class InvoiceJobEditActionController extends JobEditActionCommonController {

    @Override
    public Navigation run() throws Exception {
        
        Invoice invoice = saveJob();
        
        requestScope("jobs", invoice.getJobListRef().getModelList()); 
        requestScope("invoice", invoice); 
        // whole page refresh
        return forward("invoice-jobs.jsp");
    }
}
