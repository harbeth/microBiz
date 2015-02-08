package com.microBiz.controller.invoice;


// on invoice edit link click, load order tab first
public class EditInvoiceController extends InvoiceDetailsController {

    public String getReturnJsp() {
        return "invoice/invoice-details-wrapper.jsp";
    }
}
