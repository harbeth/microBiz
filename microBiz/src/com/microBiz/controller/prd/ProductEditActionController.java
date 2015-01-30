package com.microBiz.controller.prd;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;

import com.microBiz.meta.ProductMeta;

import com.microBiz.model.Product;

import com.microBiz.service.ProductService;



public class ProductEditActionController extends BaseController{

    private ProductService s;
    private ProductMeta metaP;
    public ProductEditActionController(){
        super();
        s = new ProductService();
        metaP = new ProductMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Product p = null;
        
        if(asKey(metaP.key)!= null){ // update
            p = s.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
            if(asString("active")==null){
                p.setActive("");
            }
        }else{ // insert new
            p = new Product();
            BeanUtil.copy(request,p); 
 
        }
        s.save(p);
        List<Product> prds = s.getAll();
        requestScope("prds", prds);
        return forward("product-list.jsp");

       
    }
    
    
}
