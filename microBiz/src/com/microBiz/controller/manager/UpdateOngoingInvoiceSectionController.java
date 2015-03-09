package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.InvoiceService;

// no use any more, remove later
public class UpdateOngoingInvoiceSectionController extends BaseController {

    private InvoiceService invoiceService;
    
    public UpdateOngoingInvoiceSectionController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
       
        int openInvoiceCount = invoiceService.getOpenInvoices().size();
        requestScope("openInvoiceCount",openInvoiceCount);
        return forward("section-ongoing-invoice.jsp");
    }
}
