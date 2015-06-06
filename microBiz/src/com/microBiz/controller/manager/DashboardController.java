package com.microBiz.controller.manager;

import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;

// no use any more, remove later
public class DashboardController extends BaseController {

    private JobService jobService;
    private InvoiceService invoiceService;
    
    public DashboardController(){
        super();
        jobService = new JobService();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
       
        int newJobReportCount = jobService.getNewJobReports().size();
        int openJobCount = jobService.getAllUncompleteJobs().size();
        List<Invoice> unPaidOffInvoices = invoiceService.getUnPaidOffInvoices();
        List<Invoice> openInvoices = invoiceService.getOpenInvoices();
        int openInvoiceCount = openInvoices.size();
        
        int unPaidOffInvoiceCount = unPaidOffInvoices.size();
        double unPaidAmt = 0;
        Iterator<Invoice> i = unPaidOffInvoices.iterator();
        while (i.hasNext()){
            Invoice inv = (Invoice)i.next();
            InvoiceReport ir = inv.getInvoiceReportRef().getModel();
            unPaidAmt = unPaidAmt + ir.getTotal()-ir.getPymtReceived();
        }
        double openInvoiceAmt = 0;
        Iterator<Invoice> ii = openInvoices.iterator();
        while (ii.hasNext()){
            Invoice inv = (Invoice)ii.next();
            InvoiceReport ir = inv.getInvoiceReportRef().getModel();
            openInvoiceAmt = openInvoiceAmt + ir.getTotal();
        }
        //requestScope("jobReports", jobReportsToAprove);
        
        // get all invoice list for now, should get not fully paied invoice list
        requestScope("newJobReportCount",newJobReportCount);
        requestScope("openJobCount",openJobCount);
        requestScope("openInvoiceCount",openInvoiceCount);
        requestScope("unPaidOffInvoiceCount",unPaidOffInvoiceCount);
        requestScope("unPaidAmt",MicroBizUtil.roundTo2Demcial(unPaidAmt));
        requestScope("openInvoiceAmt",MicroBizUtil.roundTo2Demcial(openInvoiceAmt));
        
        
        requestScope("invoices", invoiceService.getUnPaidOffInvoices());
        return forward("dashboard-wrapper.jsp");
    }
}
