package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class InventoryDetailsController extends BaseController{
    ProductService s = null;

    public InventoryDetailsController(){
        super();
        s = new ProductService();
    }
    @Override
    public Navigation run() throws Exception {

        Product p = s.get(asKey("productKey"));
        List<InventoryChange> icList = s.getAllInventoryDetailsByProduct(p.getKey());
        requestScope("product", p);
        requestScope("icList", icList);
        return forward("inventory-details.jsp");

       
    }
    
    
}

