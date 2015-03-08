package com.microBiz.controller.common;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Payment;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;

// from the link to refresh whole page

public abstract class PaymentEditController extends BaseController {

    private PaymentService paymentService;
    private InvoiceService invoiceService;
    
    public PaymentEditController(){
        super();
        paymentService = new PaymentService();
        invoiceService = new InvoiceService();
    }
    
    // assign job, create new job or edit job
    
    @Override
    public Navigation run() throws Exception {
        String invoiceKey = asString("invoiceKey");
        String paymentKey = asString("paymentKey");
        Payment payment = null;
        if ( "-1".equals(paymentKey) ) {
            // new
            payment = new Payment();
        }else{
            // edit
            payment = paymentService.get(asKey("paymentKey"));
        }
        payment.setInvoiceKey(invoiceKey);
        BeanUtil.copy(payment, request);
        requestScope("paymentTypes", paymentTypes);
        requestScope("invoice", invoiceService.get(Datastore.stringToKey(invoiceKey)));
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
}
