package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizCalculator;
import com.microBiz.controller.quote.QuoteCreateController;
import com.microBiz.model.Customer;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;

// load quote details page
public class EditQuoteController extends QuoteCreateController {

    protected QuoteService quoteService;
    protected ProductService productService;
    
    public EditQuoteController(){
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
        QuoteVersion qv = null;
        Key quoteVersionKey = asKey("quoteVersionKey");
        System.out.println("details controler: get quoteVersionKey " + asString("quoteVersionKey")) ;
        if (  quoteVersionKey == null ) {
            // from new, the first one
            qv = quoteVersionList.get(0);
            quoteVersionKey = qv.getKey();
        }
        
        requestScope("selectedKey", quoteVersionKey);
        // the first one is selected
        requestScope("quoteVersions", quoteVersionList);
        List<? extends OrderItem> orderItemList = qv.getQuoteItemsRef().getModelList();
        // set qv sub total
        qv.setSubTotal(MicroBizCalculator.getSubTotal(orderItemList));
        //System.out.println("details: cal qv sub total: " + qv.getSubTotal()); 
        BeanUtil.copy(qv, request);
        
        requestScope("orderItems", orderItemList);
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        
        requestScope("txRates", txRates);
        
        // for detail tab, if from customer page
        String customerKeyStr = asString("customerKey");
        Customer customer = null;
        if ( customerKeyStr != null ) {
            customer = customerService.get(Datastore.stringToKey(customerKeyStr));
        }
        setCustomerContactData(customer, null);
        // to edit mode
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "quote/quote-details-wrapper.jsp";
    }
}
