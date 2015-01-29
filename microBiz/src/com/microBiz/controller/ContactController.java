package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;


public class ContactController extends BaseController{

    private ContactService s;
    private CustomerService cxs;
    public ContactController(){
        super();
        s = new ContactService();
        cxs = new CustomerService();


    }
    @Override
    public Navigation run() throws Exception {

        Contact p = null;
        Customer c = null;  
        if(asKey("key") != null){// from edit contract link
            p = s.get(asKey("key"));  
            c = p.getCustomerRef().getModel();
        }else{
            c = cxs.get(asKey("customerKey")); 
            p = new Contact();
        }       
       
        BeanUtil.copy(p, request);
        requestScope("customer", c);
        return forward("contact.jsp");
    }
}
