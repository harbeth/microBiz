package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;



public class CustomerActionController extends BaseController{

    private CustomerService s;
    private CustomerMeta metaP;
    public CustomerActionController(){
        super();
        s = new CustomerService();
        metaP = new CustomerMeta();

    }
    @Override
    public Navigation run() throws Exception {

        Customer p = null;
        
        if(asKey(metaP.key)!= null){ // update
            p = s.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
            if(asString("active")==null){
                p.setActive("");
            }
        }else{ // insert new
            p = new Customer();
            BeanUtil.copy(request,p); 
 
        }
        s.save(p);
        requestScope("customer", p);
        requestScope("contacts", p.getContactListRef().getModelList());
        return forward("customerDetails.jsp");

       
    }
    
    
}
