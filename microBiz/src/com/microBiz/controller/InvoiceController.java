package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;


public class InvoiceController extends BaseController {

    private InvoiceService invoiceService;
    
    public InvoiceController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        List<Invoice> invoiceList = invoiceService.getAll();
        requestScope("invoices", invoiceList);
        return forward("invoice/invoice.jsp");
    }
}
