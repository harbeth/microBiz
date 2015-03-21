package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

// on invoice edit link click, load job tab first
public class UpdateManageSummarySectionController extends BaseController {
    
    private InvoiceService invoiceService;
    
    public UpdateManageSummarySectionController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        // for manage tab
        requestScope("invoice", invoice);
        return forward("invoice-manage-summary.jsp");
    }
}
