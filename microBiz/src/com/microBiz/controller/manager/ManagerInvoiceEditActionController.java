package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

public class ManagerInvoiceEditActionController extends BaseController {
    
    protected InvoiceService invoiceService;
    protected InvoiceMeta metaInvoice;
    
    public ManagerInvoiceEditActionController(){
        invoiceService = new InvoiceService();
        metaInvoice = new InvoiceMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // only for edit, just status and note
        Invoice invoice = invoiceService.get(asKey(metaInvoice.key));
        BeanUtil.copy(request, invoice); 
        invoice = invoiceService.save(invoice);
        // get all invoice first
        requestScope("invoices", invoiceService.getOpenInvoices());
        return forward("manager-ongoing-invoice-list.jsp");
    }
}
