package com.microBiz.controller.manager;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.JobMeta;
import com.microBiz.model.Job;
import com.microBiz.service.JobService;


// from AJAX call, on the submit form of jab details form
public class ManagerJobEditActionController extends BaseController{

    private JobService jobService;
    private JobMeta metaJob;
    
    public ManagerJobEditActionController(){
        super();
        jobService = new JobService();
        metaJob = new JobMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        Job job = jobService.get(asKey(metaJob.key));
        BeanUtil.copy(request, job);
        jobService.save(job);
        
        requestScope("jobs", jobService.getAll()); 
        // whole page refresh
        return forward("manager-uncomplete-job-list.jsp");
    }
}
