package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.PaymentEditActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Payment;


// from AJAX call, on the submit form of jab details form
public class InvoicePaymentEditActionController extends PaymentEditActionController {

    @Override
    public Navigation run() throws Exception {

        Payment payment = savePayment();
        // for invoice detail payment tab
        Invoice invoice = invoiceService.get(getInvoiceKey(payment));
        requestScope("invoice", invoice);
        requestScope("payments", invoice.getPaymentListRef().getModelList()); 
        // whole page refresh
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "invoice-payments.jsp";
    }
    
}
