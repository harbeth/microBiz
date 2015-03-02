package com.microBiz.controller.invoice;

import com.microBiz.controller.common.PaymentEditController;

// from the link to refresh whole page

public class InvoicePaymentEditController extends PaymentEditController {

    public String getReturnJsp(){
        return "invoice-payment-edit.jsp";
    }
}
