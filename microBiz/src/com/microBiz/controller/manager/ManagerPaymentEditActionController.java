package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.PaymentEditActionController;


// from AJAX call, on the submit form of jab details form
public class ManagerPaymentEditActionController extends PaymentEditActionController {

    @Override
    public Navigation run() throws Exception {
        savePayment();
        //get invoice not fully paid list
        requestScope("invoices", invoiceService.getUnPaidOffInvoices());
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "manager-unpaid-invoice-list.jsp";
    }
    
}
