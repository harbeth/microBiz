package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.ContactMeta;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;



public class ContactActionController extends BaseController {

    private ContactService s;
    private CustomerService cxs;
    private ContactMeta metaP;
    public ContactActionController(){
        super();
        s = new ContactService();
        cxs = new CustomerService();
        metaP = new ContactMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Contact p = null;
        Customer c = cxs.get(asKey("customerKey"));
        
        if(asKey(metaP.key)!= null){ // update
            p = s.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
        }else{ // insert new
            p = new Contact();
            BeanUtil.copy(request,p); 
            p.getCustomerRef().setModel(c);
            c.getContactListRef().getModelList().add(p);
            cxs.save(c);
        }

 
        s.save(p);
        requestScope("customer", c);
        requestScope("contacts", c.getContactListRef().getModelList());
        return forward("customerDetails.jsp");

       
    }
    
    
}
