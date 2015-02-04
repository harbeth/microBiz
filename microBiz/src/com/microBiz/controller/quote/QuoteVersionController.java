package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.MicroBizCalculator;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;


// load quote details page
public class QuoteVersionController extends QuoteCreateController {

    private QuoteService quoteService;
    private ProductService productService;
    
    public QuoteVersionController(){
        super();
        quoteService = new QuoteService();
        productService = new ProductService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        QuoteOrder qv = quoteService.getQuoteOrder(asKey("quoteOrderKey"));
        
        List<OrderItem> items = qv.getOrderRef().getModel().getItemsRef().getModelList();
        // set qv sub total
        qv.getOrderRef().getModel().setSubTotal(MicroBizCalculator.getSubTotal(items));
        BeanUtil.copy(qv, request);
        requestScope("orderItems", items);
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        
        requestScope("txRates", txRates);
        // to edit mode
        return forward("quote-version.jsp");
    }
}
