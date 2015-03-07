package com.microBiz.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;

public class LoadInvoicesForSalesCommissionController extends BaseController {

    private InvoiceService invoiceService;
     
    public LoadInvoicesForSalesCommissionController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        String salesStr = asString("sales");
        List<Invoice> invoices = new ArrayList<Invoice>();
        if ( salesStr != "-1" ) {
            invoices = invoiceService.getInvoicesForSalesCommission(salesStr);
            requestScope("invoices", invoices);
        }
        requestScope("invoices", invoices);
        return forward("invoices-for-sales-commission.jsp");
    }
}
