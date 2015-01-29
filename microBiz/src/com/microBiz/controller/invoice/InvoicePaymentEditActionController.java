package com.microBiz.controller.invoice;

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
public class InvoicePaymentEditActionController extends BaseController{

    private PaymentService paymentService;
    private InvoiceService invoiceService;
    
    private PaymentMeta metaPayment;
    
    public InvoicePaymentEditActionController(){
        super();
        paymentService = new PaymentService();
        invoiceService = new InvoiceService();
        metaPayment = new PaymentMeta();
    }
    
    @Override
    public Navigation run() throws Exception {

        Payment payment = new Payment();
        if ( asKey(metaPayment.key) != null ) {
            payment = paymentService.get(asKey(metaPayment.key));
        }else{
            payment = new Payment();
        }
        
        BeanUtil.copy(request, payment);
        Key invoiceKey = Datastore.stringToKey(payment.getInvoiceKey());
        Invoice invoice = invoiceService.get(invoiceKey);
        payment.getInvoiceRef().setModel(invoice);
        payment.setEnterDate();
        paymentService.save(payment);
        
        invoice = invoiceService.get(invoiceKey);
        requestScope("invoice", invoice);
        requestScope("payments", invoice.getPaymentListRef().getModelList()); 
        // whole page refresh
        return forward("invoice-payments.jsp");
    }
}
