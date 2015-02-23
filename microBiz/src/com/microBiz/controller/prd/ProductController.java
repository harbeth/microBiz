package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class ProductController extends BaseController{

    private ProductService s;

    public ProductController(){
        super();
        s = new ProductService();
 

    }
    @Override
    public Navigation run() throws Exception {

        List<Product> prdList = s.getAll();
        requestScope("prds", prdList);

        return forward("product-wrapper.jsp");

       
    }
    
    
}

