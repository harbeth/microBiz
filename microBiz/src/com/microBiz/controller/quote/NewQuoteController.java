package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.common.CustomerContactLoadController;
import com.microBiz.model.Customer;
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
        // from customer details page
        Quote quote = new Quote();

        Customer c = null;
        if(asKey("customerKey") !=null ){
            c= customerService.get(asKey("customerKey"));
            if(!c.isCommercial() && c.getAddress()!=null){
                //for residential cx, copy address
                quote.setAddress(c.getAddress());
            }
            quote.getCustomerRef().setModel(c);
        }
        // list at the top, details panel hidden first
        BeanUtil.copy(quote, request);
        
        List<Product> prodcutList = productService.getSellingPrds();
        
        requestScope("txRates", txRates);
        requestScope("products", prodcutList);
        requestScope("quoteStatus", quoteStatus);
        // for new
        setCustomerContactData(c, null);
        
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "quote-new-wrapper.jsp";
    }
}
