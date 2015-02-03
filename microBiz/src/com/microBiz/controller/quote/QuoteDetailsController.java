package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Orders;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;

// load quote details page
public class QuoteDetailsController extends BaseController {
    private QuoteService quoteService;
    private ProductService productService;
    
    public QuoteDetailsController(){
        super();
        quoteService = new QuoteService();
        productService = new ProductService();
    }
    @Override
    public Navigation run() throws Exception {
        
        // get quote key and quote version key as parameter
        Quote quote = quoteService.get(asKey("quoteKey"));
        requestScope("quote", quote);
        System.out.println("get quoteKey " + asString("quoteKey")) ;
        //based on quote key, get data for edit page
        // get quote version list, the first is selected one, get quote item list
        // ?? some times
        List<QuoteVersion> quoteVersionList = quote.getQuoteVersionsRef().getModelList();
        System.out.println("get qv list: " + quoteVersionList.size());
        // quote version key could be empty

        requestScope("quoteVersions", quoteVersionList);
        
        //default is to display the latest version in the details page
        QuoteVersion qv = quoteVersionList.get(0);  
        Orders orders = qv.getOrdersRef().getModel();
        System.out.println("get orders,  " + orders.getTotal());
        requestScope("quoteVersion", qv);
        requestScope("orders", orders);
        requestScope("orderItems", orders.getItemsRef().getModelList());
    
 
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        
        
        requestScope("txRates", txRates);
        

        return forward("quote-details.jsp");
    }
    
}
