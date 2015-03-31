package com.microBiz.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Customer;
import com.microBiz.model.Invoice;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;


public abstract class PaginatingController extends BaseController {

    public PaginatingController(){
        super();
  
    }
    
    @Override
    public abstract Navigation run() throws Exception;
    
    
    protected void setPaginatingValue(int counts){
        
      
        if(counts==0){
            requestScope("msg", "No inovice found!");
        }else{
            Integer start;
            Integer end;
            if(counts<=MicroBizConst.ENTRIES_PER_PAGE){
                
                requestScope("counts",counts);
                requestScope("start",1);
                requestScope("end",counts);
            }else{
                Integer pageNo = asInteger("pageNo");
                if(pageNo == null){
                    pageNo = new Integer(1);
                }
                start = (pageNo-1)*MicroBizConst.ENTRIES_PER_PAGE;
                end = pageNo*MicroBizConst.ENTRIES_PER_PAGE;
                if(end>counts){
                    end = counts;
                }
                int pages = counts/MicroBizConst.ENTRIES_PER_PAGE;
              
                requestScope("counts",counts);
                requestScope("start",start+1);
                requestScope("end",end);
                requestScope("pageNo", pageNo);
                requestScope("pages", pages+1);
            }
        }
       
    }
    
 
}

