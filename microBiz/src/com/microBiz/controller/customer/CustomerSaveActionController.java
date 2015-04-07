package com.microBiz.controller.customer;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;

// command controller for new/edit save
public abstract class CustomerSaveActionController extends BaseController{

    protected CustomerService customerService;
    
    protected CustomerMeta metaCustomer;
    
    public CustomerSaveActionController(){
        super();
        customerService = new CustomerService();
        metaCustomer = new CustomerMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // save invoice for edit / new
        Customer customer = getCustomer();
        BeanUtil.copy(request, customer); 
        if(asString("active")==null){
            customer.setActive("");
        }
        // should return key, if not, return to invoice list
        Key key = customerService.save(customer);
        customer.setKey(key);

        // keep on same tab, update info DIV
        requestScope("customer", customer);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
    
    public abstract Customer getCustomer();
}
