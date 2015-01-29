package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.model.Invoice;
import com.microBiz.model.Payment;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;


public class PaymentController extends BaseController{

    private PaymentService paymentService;
    private InvoiceService invoiceService;
    
    public PaymentController(){
        super();
        paymentService = new PaymentService();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
    
        Payment p = null;
        Invoice invoice = null;
        
        if(asKey("key") != null){
            // from edit contract link
            p = paymentService.get(asKey("key"));  
            invoice = p.getInvoiceRef().getModel();
        }else{
            invoice = invoiceService.get(asKey("invoiceKey"));
            p = new Payment();
        }       
       
        BeanUtil.copy(p, request);
        // get invoice
        requestScope("invoice", invoice);
        
        // f: h,  f: text
        
        return forward("payment.jsp");
    }
}
