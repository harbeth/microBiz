package com.microBiz.controller.quote;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.controller.common.PaginatingController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.model.Quote;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.QuoteService;


public abstract class QuoteSearchBaseController extends PaginatingController {

    // for customer drop down change
    protected QuoteService quoteService;
    
    public QuoteSearchBaseController(){
        super();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        List<Quote> quoteList = getQuotes();
        int counts =  quoteList.size();
        setPaginatingValue(counts);
        Integer pageNo = asInteger("pageNo");
        Integer start;
        Integer end;
        if(counts<=MicroBizConst.ENTRIES_PER_PAGE){
            requestScope("quotes", quoteList);
 
        }else{
            
            if(pageNo == null){
                pageNo = new Integer(1);
            }
            start = (pageNo-1)*MicroBizConst.ENTRIES_PER_PAGE;
            end = pageNo*MicroBizConst.ENTRIES_PER_PAGE;
            if(end>counts){
                end = counts;
            }
           
            requestScope("quotes", quoteList.subList(start.intValue(), end.intValue()));
 
        }
        requestScope("quoteStatus",quoteStatus);
        return forward(getReturnedJsp());
    }
    
    public abstract List<Quote> getQuotes();
    public abstract String getReturnedJsp();
    
}

