package com.microBiz.controller.prd;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class InventoryAddController extends BaseController{
    ProductService s = null;

    public InventoryAddController(){
        super();
        s = new ProductService();
    }
    @Override
    public Navigation run() throws Exception {

        Product p = s.get(asKey("productKey"));
        InventoryChange ic = new InventoryChange();
     
        BeanUtil.copy(ic, request);
        requestScope("product", p);
        return forward("inventory-add.jsp");

       
    }
    
    
}

