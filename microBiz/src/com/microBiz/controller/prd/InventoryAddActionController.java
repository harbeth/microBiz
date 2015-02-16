package com.microBiz.controller.prd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class InventoryAddActionController extends BaseController{

    private ProductService s;

    public InventoryAddActionController(){
        super();
        s = new ProductService();


    }
    @Override
    public Navigation run() throws Exception {

        Product p = s.get(asKey("productKey"));
        InventoryChange ic = new InventoryChange();
        BeanUtil.copy(request,ic); 
        ic.setOriginalQty(p.getCurrentQty());
        ic.setNotes("add inventory");
        Double newQty = p.getCurrentQty() + ic.getChangeQty();
        p.setCurrentQty(newQty);
        ic.setNewQty(newQty);


        s.save(p, ic);
        List<Product> prds = s.getAll();
        requestScope("prds", prds);
        return forward("inventory-list.jsp");

       
    }
    
    
}
