package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.service.JobService;

// no use any more, remove later
public class UpdateUncompleteJobSectionController extends BaseController {

    private JobService jobService;
    
    public UpdateUncompleteJobSectionController(){
        super();
        jobService = new JobService();
    }
    
    @Override
    public Navigation run() throws Exception {
       
        int openJobCount = jobService.getAllUncompleteJobs().size();
        requestScope("openJobCount",openJobCount);
        return forward("section-uncomplete-job.jsp");
    }
}
