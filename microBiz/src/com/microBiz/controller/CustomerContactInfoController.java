package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Contact;
import com.microBiz.service.ContactService;


public class CustomerContactInfoController extends BaseController{

    // for customer drop down
    private ContactService contactService;
    
    public CustomerContactInfoController(){
        super();
        contactService = new ContactService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // pass customerKey in parameter to get contact list
        String contactKey = asString("contactKey");
        Contact contact = contactService.get(Datastore.stringToKey(contactKey));
        // get contact list
        boolean contactInfoShown = false;
        if ( contact != null ) {
            contactInfoShown = true;
            requestScope("contact", contact);
        }
        requestScope("contactInfoShown", contactInfoShown);
        return forward("customer-contact-info.jsp");
    }
}
