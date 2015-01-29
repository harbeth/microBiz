package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;


public class CustomerContactChangeController extends BaseController {

    // for customer drop down change
    private CustomerService customerService;
    
    public CustomerContactChangeController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // pass customerKey in parameter to get contact list
        String customerKey = asString("customerKey");
        Customer customer = customerService.get(Datastore.stringToKey(customerKey));
        // get contact list
        boolean contactInfoShown = false;
        List<Contact> contactList = customer.getContactListRef().getModelList();
        if ( contactList != null && contactList.size() > 0 ) {
            contactInfoShown = true;
            requestScope("contacts", contactList);
            // by default, show the first one
            Contact contact = contactList.get(0);
            contact.setSelected(true);
            requestScope("contact", contact);
        }
        requestScope("contactInfoShown", contactInfoShown);
        return forward("customer-contact.jsp");
    }
}
