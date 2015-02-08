package com.microBiz.controller.quote;

import org.slim3.controller.Navigation;

import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Order;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.QuoteService;


// load quote details page
public class QuoteOrderController extends OrderLoadActionController {

    private QuoteService quoteService;
    
    public QuoteOrderController(){
        super();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // load order items when clicking on the quote order
        QuoteOrder quoteOrder = quoteService.getQuoteOrder(asKey("quoteOrderKey"));
        Order order = quoteOrder.getOrderRef().getModel();
        setOrderData(order);
        requestScope("quoteOrder", quoteOrder);
        // to edit mode
        return forward("quote-order.jsp");
    }
}
