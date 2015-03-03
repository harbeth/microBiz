package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;

// from the link to refresh whole page

public class ManagerJobEditController extends BaseController{

    private JobService jobService;
    
    public ManagerJobEditController(){
        super();
        jobService = new JobService();
    }
    
    // assign job, create new job or edit job
    
    @Override
    public Navigation run() throws Exception {
         // edit
        Job job = jobService.get(asKey("jobKey"));
        BeanUtil.copy(job, request);
        requestScope("jobStatus", jobStatus);
        return forward("manager-job-edit.jsp");
    }
}
