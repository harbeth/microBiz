package com.microBiz.controller.common;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.controller.BaseController;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

public abstract class CustomerContactLoadController extends BaseController {

    // common logic to load customer and contact drop down together
    // for customer drop down
    protected CustomerService customerService;
    
    public CustomerContactLoadController(){
        super();
        customerService = new CustomerService();
    }
    
    @Override
    public Navigation run() throws Exception {
        return null;
    }
    
    public void setCustomerContactData(Customer selectedCustomer, Contact selectedContact) throws Exception {
  
        boolean customerContactShown = false;
        boolean contactInfoShown = false;
        
        // get all customer
        List<Customer> customerList = customerService.getActiveCustomers();
        // find out which one selected
        if ( customerList != null && customerList.size() > 0 ) {
            // if has customer
            if ( selectedCustomer != null ) {
                for ( Customer c : customerList ) {
                    if ( selectedCustomer.equals(c) ) {
                        c.setSelected(true);
                        // has customer, get contact list info
                        List<Contact> contactList = c.getContactListRef().getModelList();
                        if ( contactList != null && contactList.size() > 0 ) {
                            // has contact
                            customerContactShown = true;
                            // set contact selection
                            if ( selectedContact != null ) {
                                for ( Contact contact : contactList ) {
                                    if ( selectedContact.equals(contact) ) {
                                        contact.setSelected(true);
                                        contactInfoShown = true;
                                        requestScope("contact", contact);
                                    }
                                }
                            }
                            requestScope("contacts", contactList);
                        }
                    }
                }
            }
        }
        requestScope("customers", customerList);
        if ( selectedCustomer != null ) {
            requestScope("customerKey", Datastore.keyToString(selectedCustomer.getKey()));
            requestScope("customerName", selectedCustomer.getName());
        }
        // show contact DIV flag
        requestScope("customerContactShown", customerContactShown);
        requestScope("contactInfoShown", contactInfoShown);
    }
}
