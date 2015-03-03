package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.InvoiceService;

// no use any more, remove later
public class ManagerOngoingInvoiceController extends BaseController {

    private InvoiceService invoiceService;
    
    public ManagerOngoingInvoiceController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        //List<JobReport> jobReportsToApprove = jobService.getNewJobReports();
        //requestScope("jobReports", jobReportsToApprove);
        
        // get all invoice list for now, should get not fully paied invoice list
        requestScope("invoices", invoiceService.getAll());
        return forward("manager-ongoing-invoice-list.jsp");
    }
}
