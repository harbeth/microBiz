package com.microBiz.controller.prd;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.PrdRatio;
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
        }else{ // insert new
            p = new Product();  
            p.setCurrentQty(new Double(0));
        }
        BeanUtil.copy(request,p); 
        if(asString("active")==null){
            p.setActive("");
        }
       
        String[] ratios = paramValues("ratios");
        String[] ratioDescs = paramValues("ratioDescs");
        List<PrdRatio> prdRs = new ArrayList<PrdRatio>();
        for (int i = 1; i< ratioDescs.length;i++){
            PrdRatio pr = new PrdRatio();
            pr.setDesc(ratioDescs[i]);
            pr.setRatio(Double.parseDouble(ratios[i]));     
            prdRs.add(pr);
        }
        s.save(p, prdRs);
        List<Product> prds = s.getAll();
        requestScope("prds", prds);
        return forward("product-list.jsp");

       
    }
    
    
}
