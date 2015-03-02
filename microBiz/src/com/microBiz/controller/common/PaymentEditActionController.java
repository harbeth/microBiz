package com.microBiz.controller.common;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.PaymentMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Payment;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;


// from AJAX call, on the submit form of jab details form
public abstract class PaymentEditActionController extends BaseController {

    protected PaymentService paymentService;
    protected InvoiceService invoiceService;
    
    protected PaymentMeta metaPayment;
    
    public PaymentEditActionController(){
        super();
        paymentService = new PaymentService();
        invoiceService = new InvoiceService();
        metaPayment = new PaymentMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        return null;
    }
    
    protected Payment savePayment() {
        Payment payment = new Payment();
        if ( asKey(metaPayment.key) != null ) {
            payment = paymentService.get(asKey(metaPayment.key));
        }else{
            payment = new Payment();
        }
        
        BeanUtil.copy(request, payment);
        Key invoiceKey = getInvoiceKey(payment);
        Invoice invoice = invoiceService.get(invoiceKey);
        payment.getInvoiceRef().setModel(invoice);
        paymentService.save(payment);
        return payment;
    }
    
    public Key getInvoiceKey(Payment payment) {
        return Datastore.stringToKey(payment.getInvoiceKey());
    }
    
    public abstract String getReturnJsp();
}
