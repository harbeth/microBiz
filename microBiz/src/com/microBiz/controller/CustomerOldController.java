package com.microBiz.controller;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Customer;
import com.microBiz.service.CustomerService;





public class CustomerOldController extends BaseController{

    private CustomerService s;
    private CustomerMeta metaP;
    public CustomerOldController(){
        super();
        s = new CustomerService();
        metaP = new CustomerMeta();

    }
    @Override
    public Navigation run() throws Exception {

    
        Customer p = null;
        if(asKey(metaP.key) != null){// from edit link
            p = s.get(asKey(metaP.key));         
        }else{
            p = new Customer();
        }
        BeanUtil.copy(p, request);


        requestScope("cxTypes", cxTypes);
        requestScope("ratings", cxRatings);
        requestScope("units", units);
        requestScope("customers", s.getAll());
        return forward("customer.jsp");

       
    }
    
    
}
