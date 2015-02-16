package com.microBiz.controller.jobReport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.model.JobReport;
import com.microBiz.model.Product;
import com.microBiz.service.JobService;
import com.microBiz.service.ProductService;

public class JobReportNewController extends BaseController {

    private JobService jobService;
    private ProductService productService;

    public JobReportNewController() {
        super();
        jobService = new JobService();
        productService = new ProductService();
    }

    @Override
    public Navigation run() throws Exception {

        Job job = jobService.get(asKey("jobKey"));
        JobReport jr = new JobReport();

        List<String> prdKeys = job.getUsePrdKeys();

        List<Product> prds = new ArrayList();

        Iterator i = prdKeys.iterator();
        while (i.hasNext()) {
            String keyStr = (String) i.next();
            Product prd = productService.get(Datastore.stringToKey(keyStr));
            prds.add(prd);
        }
        requestScope("prds", prds);

        BeanUtil.copy(jr, request);

        requestScope("job", job);
        return forward("job-report-new.jsp");
    }

}
