package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.SupplierMeta;
import com.microBiz.model.Supplier;
import com.microBiz.service.SupplierService;



public class SupplierController extends BaseController{

    private SupplierService s;
    private SupplierMeta metaS;

    public SupplierController(){
        super();
        s = new SupplierService();
        metaS = new SupplierMeta();
 

    }
    @Override
    public Navigation run() throws Exception {
        Supplier supplier = null;
        if(asKey(metaS.key) != null){// from edit link
            supplier = s.get(asKey(metaS.key));         
        }else{
            supplier = new Supplier();
         
        }
        BeanUtil.copy(supplier, request);
        List<Supplier> suppliers = s.getAll();
        requestScope("suppliers", suppliers);

        return forward("supplier-wrapper.jsp");

       
    }
    
    
}

