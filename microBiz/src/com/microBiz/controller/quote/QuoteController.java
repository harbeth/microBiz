package com.microBiz.controller.quote;

import java.util.List;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Quote;
import com.microBiz.service.QuoteService;


public class QuoteController extends BaseController {

    private QuoteService quoteService;
    
    public QuoteController(){
        super();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // only get data for invoice list, not details
        List<Quote> quoteList = quoteService.getAll();
        requestScope("quotes", quoteList);
        return forward("quote.jsp");
    }
}
