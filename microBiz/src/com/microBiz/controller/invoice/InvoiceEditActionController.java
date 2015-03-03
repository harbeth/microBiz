package com.microBiz.controller.invoice;

import com.microBiz.model.Invoice;

//after save for edit, keep the same tab and update info DIV

public class InvoiceEditActionController extends InvoiceSaveActionController {

    public Invoice getInvoice() {
        // no for new
        return invoiceService.get(asKey(metaInvoice.key));
    }
    
    public String getReturnJsp() {
        return "invoice-detail-info.jsp";
    }
}
