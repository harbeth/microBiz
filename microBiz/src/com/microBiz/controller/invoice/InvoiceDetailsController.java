package com.microBiz.controller.invoice;

import com.microBiz.controller.EditInvoiceController;

// on invoice edit link click, load job tab first
public class InvoiceDetailsController extends EditInvoiceController {

    public String getReturnJsp() {
        return "invoice-details.jsp";
    }
}
