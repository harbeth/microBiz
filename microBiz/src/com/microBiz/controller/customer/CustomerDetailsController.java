package com.microBiz.controller.customer;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Quote;
import com.microBiz.service.CustomerService;

// on invoice edit link click, load job tab first
public class CustomerDetailsController extends BaseController {

    private CustomerService customerService;
    
    public CustomerDetailsController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {

        // list at the top, details panel hidden first
        Customer customer = customerService.get(asKey("customerKey")); 
        
        requestScope("customer", customer);
        requestScope("contacts", customer.getContactListRef().getModelList());
        
        // get data for invoice tab
        HttpSession session = request.getSession();
        String myRole = (String)session.getAttribute("myrole");
        String userName = (String)session.getAttribute("userName");
        
        List<Quote> quotes = customer.getQuoteListRef().getModelList();
        
        if(myRole.equals("sales")){
            Iterator<Quote> i = quotes.iterator();
            while (i.hasNext()){
                Quote q = (Quote)i.next();
                if(q.getCreatorName()==null || !q.getCreatorName().equals(userName)){
                    i.remove();
                }
            }
       
        }
        requestScope("quotes", quotes);
        
        return forward("customer-details.jsp");
    }
}
