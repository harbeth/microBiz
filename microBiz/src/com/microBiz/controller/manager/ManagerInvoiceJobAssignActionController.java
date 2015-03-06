package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.JobEditActionCommonController;


// from AJAX call, on the submit form of jab details form
public class ManagerInvoiceJobAssignActionController extends JobEditActionCommonController {

    @Override
    public Navigation run() throws Exception {
        
        saveJob();
        
        requestScope("invoices", invoiceService.getOpenInvoices());
        // whole page refresh
        return forward("manager-ongoing-invoice-list.jsp");
    }
}
