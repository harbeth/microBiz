package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Payment;
import com.microBiz.service.PaymentService;

// from the link to refresh whole page

public class InvoicePaymentEditController extends BaseController{

    private PaymentService paymentService;
    
    public InvoicePaymentEditController(){
        super();
        paymentService = new PaymentService();
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
        return forward("invoice-payment-edit.jsp");
    }
}
