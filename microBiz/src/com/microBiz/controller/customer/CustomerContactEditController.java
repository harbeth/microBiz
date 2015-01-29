package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Contact;
import com.microBiz.service.ContactService;

// on invoice edit link click, load job tab first
public class CustomerContactEditController extends BaseController{

    private ContactService contactService;
    
    public CustomerContactEditController(){
        super();
        contactService = new ContactService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        String customerKey = asString("customerKey");
        String contactKey = asString("contactKey");
        Contact contact = null;
        if ( "-1".equals(contactKey) ) {
            // new
            contact = new Contact();
        }else{
            // edit
            contact = contactService.get(asKey("contactKey"));
        }
        contact.setCustomerKey(customerKey);
        BeanUtil.copy(contact, request);
        return forward("customer-contact-edit.jsp");
    }
}
