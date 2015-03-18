package com.microBiz.controller.quote;

import java.util.List;

import javax.servlet.http.HttpSession;

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
        List<Quote> quoteList = null;
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        if(myRole.equals("sales")){
            quoteList = quoteService.getQuotesByUser((String)session.getAttribute("userName"));
        }else{
            quoteList = quoteService.getAll();
        }
        requestScope("quotes", quoteList);
        return forward("quote.jsp");
    }
}
