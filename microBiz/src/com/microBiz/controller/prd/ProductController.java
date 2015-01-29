package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class ProductController extends BaseController{

    private ProductService s;
    private ProductMeta metaP;
    public ProductController(){
        super();
        s = new ProductService();
        metaP = new ProductMeta();

    }
    @Override
    public Navigation run() throws Exception {

    
        Product p = null;
        if(asKey(metaP.key) != null){// from edit link
            p = s.get(asKey(metaP.key));         
        }else{
            p = new Product();
        }
        BeanUtil.copy(p, request);
        List<Product> prdList = s.getAll();
        requestScope("prds", prdList);
        requestScope("prdTypes", prdTypes);
        requestScope("suppliers", suppliers);
        requestScope("units", units);
        return forward("product-wrapper.jsp");

       
    }
    
    
}

