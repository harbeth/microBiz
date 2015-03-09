package com.microBiz.controller.manager;

import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.service.InvoiceService;

// no use any more, remove later
public class UpdateUnpaidSectionController extends BaseController {

    private InvoiceService invoiceService;
    
    public UpdateUnpaidSectionController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
       
        List<Invoice> unPaidOffInvoices = invoiceService.getUnPaidOffInvoices();
        
        int unPaidOffInvoiceCount = unPaidOffInvoices.size();
        double unPaidAmt = 0;
        Iterator<Invoice> i = unPaidOffInvoices.iterator();
        while (i.hasNext()){
            Invoice inv = (Invoice)i.next();
            InvoiceReport ir = inv.getInvoiceReportRef().getModel();
            unPaidAmt = unPaidAmt + ir.getTotal()-ir.getPymtReceived();
        }
        // get all invoice list for now, should get not fully paied invoice list
        requestScope("unPaidOffInvoiceCount",unPaidOffInvoiceCount);
        requestScope("unPaidAmt",unPaidAmt);
        
        return forward("section-unpaid-invoice.jsp");
    }
}
