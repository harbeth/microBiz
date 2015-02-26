package com.microBiz.controller.manager;

import org.slim3.datastore.Datastore;

import com.microBiz.controller.jobReport.JobReportEditController;
import com.microBiz.model.JobReport;


public class ManagerJobReportEditController extends JobReportEditController {
        @Override
        public String getReturnedJsp() {
            return "manager-job-report-edit.jsp";
        }
        
        public void setRequestScope(JobReport jr){
            requestScope("jobReportKey", Datastore.keyToString(jr.getKey()));
        }


}
