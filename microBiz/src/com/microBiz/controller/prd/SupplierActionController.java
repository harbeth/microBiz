package com.microBiz.controller.prd;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.SupplierMeta;
import com.microBiz.model.Supplier;
import com.microBiz.service.SupplierService;



public class SupplierActionController extends BaseController{
    private SupplierService s;
    private SupplierMeta metaP;
    public SupplierActionController(){
        super();
        s = new SupplierService();
        metaP = new SupplierMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Supplier p = null;
        
        if(asKey(metaP.key)!= null){ // update
            p = s.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
            if(asString("active")==null){
                p.setActive("");
            }
        }else{ // insert new
            p = new Supplier();
            
            BeanUtil.copy(request,p); 
        
 
        }
        s.save(p);
        List<Supplier> suppliers = s.getAll();
        requestScope("suppliers", suppliers);
        return forward("supplier-wrapper.jsp");

       
    }
    
    
}
