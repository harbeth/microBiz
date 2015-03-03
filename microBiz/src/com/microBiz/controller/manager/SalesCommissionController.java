package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;

// no use any more, remove later
public class SalesCommissionController extends BaseController {

    private MiUserService userService;
     
    public SalesCommissionController(){
        super();
        userService = new MiUserService();
       
    }
    
    @Override
    public Navigation run() throws Exception {
        
        //List<JobReport> jobReportsToApprove = jobService.getNewJobReports();
        //requestScope("jobReports", jobReportsToApprove);
        
        // get all invoice list for now, should get not fully paied invoice list
        requestScope("salesList", userService.getSales());
        return forward("sales-commission-wrapper.jsp");
    }
}
