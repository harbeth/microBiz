package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.PaymentMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Payment;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;



public class PaymentActionController extends BaseController{

    private PaymentService paymentService;
    private InvoiceService invoiceService;
    
    private PaymentMeta metaP;
    public PaymentActionController(){
        super();
        paymentService = new PaymentService();
        invoiceService = new InvoiceService();
        metaP = new PaymentMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Payment p = null;
        if(asKey(metaP.key)!= null){ // update
            p = paymentService.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
        }else{ // insert new
            p = new Payment();
            BeanUtil.copy(request,p); 
            //p.getInvoiceRef().setModel(i);
            //c.getContactListRef().getModelList().add(p);
            //cxs.save(c);
        }
 
        paymentService.save(p);
        
        Invoice i = invoiceService.get(asKey("invoiceKey"));
        requestScope("invoice", i);
        requestScope("payments", i.getPaymentListRef().getModelList());
        return forward("invoiceDetails.jsp");

       
    }
    
    
}
