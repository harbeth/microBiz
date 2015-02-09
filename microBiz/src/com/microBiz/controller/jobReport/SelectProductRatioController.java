package com.microBiz.controller.jobReport;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Job;
import com.microBiz.model.JobMaterialReport;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;
import com.microBiz.service.JobService;


public class SelectProductRatioController extends BaseController {


    private ProductService productService;
    
    public SelectProductRatioController(){
        super();

        productService  = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
  
        Product p =productService.get(asKey("productKey"));
        if(p.getPrdRatioList()!=null && p.getPrdRatioList().size()>1){
            requestScope("showPrdRatio", true);
        }
        requestScope("product", p);
        return forward("select-product-ratio.jsp");
    }
}
