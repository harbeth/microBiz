package com.microBiz.controller.quote;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Quote;
import com.microBiz.service.QuoteService;


public class QuoteSearchController extends QuoteSearchBaseController {

    @Override
    public List<Quote> getQuotes() {
        List<Quote> quoteList = null;
        String searchAddrStr = asString("searchQuoteByAddr");
        Integer status = asInteger("searchStatus");
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        
        if(searchAddrStr==null){
            if(myRole.equals("sales")){
                if(status ==null ||status.intValue()==0 ){
                    quoteList = quoteService.getQuotesByUser((String)session.getAttribute("userName"));
                }else{
                    quoteList = quoteService.getQuotesByUserStatus((String)session.getAttribute("userName"),status);
                }
            }else{
                if(status ==null ||status.intValue()==0 ){
                    quoteList = quoteService.getAll();
                }else{
                    quoteList = quoteService.getQuotesByStatus(status);
                }
            }
            
        }else{
            if(myRole.equals("sales")){
                if(status ==null ||status.intValue()==0 ){
                    quoteList = quoteService.searchQuoteByUserAddrStarts((String)session.getAttribute("userName"),searchAddrStr);
                }else{
                    quoteList = quoteService.searchQuoteByUserStatusAddrStarts((String)session.getAttribute("userName"),searchAddrStr,status);
                }
            }else{
                if(status ==null ||status.intValue()==0 ){
                    quoteList = quoteService.searchQuoteAddrStartWith(searchAddrStr);
                }else{
                    quoteList = quoteService.searchQuoteByStatusAddrStartWith(searchAddrStr,status); 
                }
            }
        }
 
        return quoteList;
    }

    @Override
    public String getReturnedJsp() {
        return "quote-list-div.jsp";
    }
}
