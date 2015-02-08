package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.QuoteService;

// load quote details page
public class QuoteDetailsController extends BaseController {
    
    private QuoteService quoteService;
    
    public QuoteDetailsController(){
        super();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // get quote key to get quote order list
        Quote quote = quoteService.get(asKey("quoteKey"));
        requestScope("quote", quote);
        System.out.println("get quoteKey " + asString("quoteKey")) ;
        //based on quote key, get data for edit page
        // get quote version list, the first is selected one, get quote item list
        // ?? some times
        List<QuoteOrder> quoteOrderList = quote.getQuoteOrderRef().getModelList();
        System.out.println("get quote order list: " + quoteOrderList);
        // quote order key could be empty
        requestScope("quoteOrders", quoteOrderList);
        
        return forward(getReturnJsp());
    }
    
    public String getReturnJsp() {
        return "quote-details.jsp";
    }
    
}
