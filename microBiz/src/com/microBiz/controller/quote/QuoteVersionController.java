package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.MicroBizCalculator;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.model.QuoteVersion;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteVersionService;

// load quote details page
public class QuoteVersionController extends QuoteCreateController {

    private QuoteVersionService quoteVersionService;
    private ProductService productService;
    
    public QuoteVersionController(){
        super();
        quoteVersionService = new QuoteVersionService();
        productService = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        QuoteVersion qv = quoteVersionService.get(asKey("quoteVersionKey"));
        
        List<? extends OrderItem> orderItemList = qv.getQuoteItemsRef().getModelList();
        // set qv sub total
        qv.setSubTotal(MicroBizCalculator.getSubTotal(orderItemList));
        BeanUtil.copy(qv, request);
        requestScope("orderItems", orderItemList);
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        
        requestScope("txRates", txRates);
        // to edit mode
        return forward("quote-version.jsp");
    }
}
