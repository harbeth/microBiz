package com.microBiz.controller.invoice;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

// no use any more, remove later
public class InvoiceListController extends BaseController {

    private InvoiceService invoiceService;
    
    public InvoiceListController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        List<Invoice> invoiceList = invoiceService.getAll();
        requestScope("invoices", invoiceList);
        return forward("invoice.jsp");
    }
}
