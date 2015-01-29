package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;


// click on Job tab, show job list
public class InvoicePaymentsController extends BaseController{

    private InvoiceService invoiceService;
    
    public InvoicePaymentsController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // need to get invoiceKey
        Invoice invoice = invoiceService.get(asKey("invoiceKey"));
        requestScope("invoice", invoice);
        requestScope("payments", invoice.getPaymentListRef().getModelList());
        return forward("invoice-payments.jsp");
    }
}
