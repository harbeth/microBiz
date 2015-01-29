package com.microBiz.controller.prd;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.Product;
import com.microBiz.service.InventoryChangeService;
import com.microBiz.service.ProductService;



public class InventoryController extends BaseController{


    private InventoryChangeService ics;
    private ProductService ps;

    public InventoryController(){
        super();

        ics = new InventoryChangeService();
        ps = new ProductService();


    }
    @Override
    public Navigation run() throws Exception {

    
        InventoryChange ic = new InventoryChange();
        BeanUtil.copy(ic, request);
        List<Product> rawPrds = ps.getReportingPrds();
        requestScope("prds", rawPrds);
        requestScope("inventoryChangeTypes", inventoryChangeTypes);


        return forward("inventory-wrapper.jsp");

       
    }
    
    
}

