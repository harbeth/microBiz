package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Product;
import com.microBiz.service.ProductService;



public class InventoryController extends BaseController{


    private ProductService ps;

    public InventoryController(){
        super();

        ps = new ProductService();


    }
    @Override
    public Navigation run() throws Exception {

        List<Product> rawPrds = ps.getReportingPrds();
        requestScope("prds", rawPrds);
        return forward("inventory-wrapper.jsp");

       
    }
    
    
}

