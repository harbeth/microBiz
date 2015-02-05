package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.CustomerContactLoadController;

import com.microBiz.model.Customer;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.service.ProductService;

public class QuoteCreateController extends CustomerContactLoadController {
    
    protected ProductService productService;

    
    public QuoteCreateController(){
        super();
        productService = new ProductService();

    }
    
    @Override
    public Navigation run() throws Exception {
        
        Quote quote = new Quote();
        quote.setStatus("open");
        String forwardJsp = "";
        Customer c = null;
        if(asKey("customerKey")!=null){// from customer details page
            c= customerService.get(asKey("customerKey"));
            if(!c.isCommercial() && c.getAddress()!=null){//for residential cx, copy address
                quote.setAddress(c.getAddress());
            }
            quote.getCustomerRef().setModel(c);
            forwardJsp = "quote-new-wrapper.jsp";
        }else{
            forwardJsp = "quote-new.jsp";
        }
                
        // list at the top, details panel hidden first
       
        BeanUtil.copy(quote, request);
        
        List<Product> prodcutList = productService.getSellingPrds();
        
        requestScope("txRates", txRates);
        requestScope("products", prodcutList);
        requestScope("quoteStatus", quoteStatus);
        // for new
        setCustomerContactData(c, null);
        
        return forward(forwardJsp);
    }

}
