package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.ContactMeta;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;


// from AJAX call, on the submit form of jab details form
public class CustomerContactEditActionController extends BaseController{

    private CustomerService customerService;
    private ContactService contactService;
    
    private ContactMeta metaContact;
    
    public CustomerContactEditActionController(){
        super();
        contactService = new ContactService();
        customerService = new CustomerService();
        metaContact = new ContactMeta();
    }
    
    @Override
    public Navigation run() throws Exception {

        Contact contact = new Contact();
        if ( asKey(metaContact.key) != null ) {
            contact = contactService.get(asKey(metaContact.key));
        }else{
            contact = new Contact();
        }
        //Customer customer = contact.getCustomerRef().getModel();
        BeanUtil.copy(request, contact);
        Key customerKey = Datastore.stringToKey(contact.getCustomerKey());
        Customer customer = customerService.get(customerKey);
        contact.getCustomerRef().setModel(customer);
        contactService.save(contact);
        
        customer = customerService.get(customerKey);
        requestScope("customer", customer);
        requestScope("contacts", customer.getContactListRef().getModelList()); 
        // whole page refresh
        return forward("customer-contacts.jsp");
    }
}
