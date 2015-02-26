package com.microBiz.controller.jobReport;

import com.microBiz.model.JobReport;


public class InstallerJobReportEditController extends JobReportEditController {
        @Override
        public String getReturnedJsp() {
            return "job-report-edit.jsp";
        }

        @Override
        public void setRequestScope(JobReport jr) {
            
        }


}
