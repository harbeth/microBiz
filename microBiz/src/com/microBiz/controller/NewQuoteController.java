package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.CustomerContactLoadController;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;

public class NewQuoteController extends CustomerContactLoadController {
    
    protected ProductService productService;
    
    public NewQuoteController(){
        super();
        productService = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // list at the top, details panel hidden first
        Quote quote = new Quote();
        BeanUtil.copy(quote, request);
        
        List<Product> prodcutList = productService.getSellingPrds();
        
        requestScope("txRates", txRates);
        requestScope("products", prodcutList);
        // for new
        setCustomerContactData(null, null);
        
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "quote/quote-new-wrapper.jsp";
    }
}
