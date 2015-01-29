package com.microBiz.controller;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

// on invoice edit link click, load job tab first
public class EditInvoiceController extends BaseController{

    private InvoiceService invoiceService;
    
    public EditInvoiceController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {

        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        requestScope("invoice", invoice); 
        // get jobs, by default job tab is shown
        requestScope("jobs", invoice.getJobListRef().getModelList()); 
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "invoice/invoice-details-wrapper.jsp";
    }
}
