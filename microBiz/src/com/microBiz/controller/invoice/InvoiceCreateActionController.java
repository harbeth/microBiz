package com.microBiz.controller.invoice;

import com.microBiz.model.Invoice;

public class InvoiceCreateActionController extends InvoiceSaveActionController {

    public Invoice getInvoice() {
        return new Invoice();
    }
    
    public String getReturnJsp() {
        return "invoice-details.jsp";
    }
}
