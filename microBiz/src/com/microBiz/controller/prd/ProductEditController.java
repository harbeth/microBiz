package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class ProductEditController extends BaseController{

    private ProductService s;

    public ProductEditController(){
        super();
        s = new ProductService();


    }
    @Override
    public Navigation run() throws Exception {

    
        Product p = null;
        List<PrdRatio> prdRs = null;
        if(asKey("productKey")  != null){// from edit link
            p = s.get(asKey("productKey"));   
            prdRs = p.getPrdRatioList();
            
        }else{
            p = new Product();

        }
        BeanUtil.copy(p, request);
        requestScope("prdTypes", prdTypes);
        requestScope("suppliers", suppliers);
        requestScope("units", units); 
        if(prdRs!=null){
            requestScope("prdRatios", prdRs);
        }
        return forward("product-edit.jsp");

       
    }
    
    
}

