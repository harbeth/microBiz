package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.meta.InvoiceExpenseMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceExpense;
import com.microBiz.model.InvoiceReport;



public class InvoiceExpenseService {
    private InvoiceExpenseMeta e = new InvoiceExpenseMeta();
 
    public List<InvoiceExpense> getAll() {
        return Datastore.query(e).asList();
    }
    
    public InvoiceExpense get(Key key) {
        return Datastore.get(e, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(InvoiceExpense e) {
        Invoice invoice = e.getInvoiceRef().getModel();
        InvoiceReport ir = invoice.getInvoiceReportRef().getModel();
        if(e.getKey()==null){//new payment
            if(e.getCanceled()==null || !e.getCanceled().equals("on")){
                ir.addOtherExpense(e.getExpense());
                if(e.getForSalesCommission().equals("on")){
                    invoice.setSalesPaid(true);
                    Datastore.put(invoice);
                }
            }
        }else{
            if(e.getCanceled().equals("on")){//cancel payment
                ir.addOtherExpense(e.getExpense()*(-1)); 
            }else{
                InvoiceExpense olde = get(e.getKey());
                if(olde.getExpense()!=e.getExpense()){//amount changed
                    ir.addOtherExpense(e.getExpense()-olde.getExpense());
                }
            }
            
        }
        Datastore.put(e);
        Datastore.put(ir);
        
    }
    

    
 
}
