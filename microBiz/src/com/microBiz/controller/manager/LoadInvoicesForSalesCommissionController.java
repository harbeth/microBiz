package com.microBiz.controller.manager;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;

// no use any more, remove later
public class LoadInvoicesForSalesCommissionController extends BaseController {

    private InvoiceService invoiceService;
     
    public LoadInvoicesForSalesCommissionController(){
        super();
        invoiceService = new InvoiceService();
       
    }
    
    @Override
    public Navigation run() throws Exception {
        
        List<Invoice> invoices = invoiceService.getInvoicesForSalesCommission(asString("sales"));
        
        requestScope("invoices", invoices);
        return forward("invoices-for-sales-commission.jsp");
    }
}
