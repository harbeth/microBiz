package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;


// from AJAX call, on the submit form of jab details form
public class InvoicePaymentListController extends BaseController{

    private InvoiceService invoiceService;
    
    public InvoicePaymentListController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        requestScope("payments", invoice.getPaymentListRef().getModelList()); 
        requestScope("invoice", invoice);
        // whole page refresh
        return forward("invoice-payments.jsp");
    }
}
