package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.PaymentMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.model.Payment;



public class PaymentService {
    
    private PaymentMeta p = new PaymentMeta();
 
    
    public List<Payment> getAll() {
        return Datastore.query(p).asList();
    }
    
    public Payment get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(Payment p) {
        Invoice invoice = p.getInvoiceRef().getModel();
        InvoiceReport ir = invoice.getInvoiceReportRef().getModel();
        if(p.getKey()==null){//new payment
            if(!p.getCanceled().equals("on")){
                ir.addPymtReceived(p.getAmount());
            }
        }else{//edit
            if(p.getCanceled().equals("on")){//cancel payment
                ir.addPymtReceived(p.getAmount()*(-1)); 
            }else{
                Payment oldPymt = get(p.getKey());
                if(oldPymt.getAmount()!=p.getAmount()){//amount changed
                    ir.addPymtReceived(p.getAmount()-oldPymt.getMethod());
                }
            }
            
        }
        
        if(ir.getPymtReceived()>=ir.getTotal()){
            invoice.setPaidOff(true);
            Datastore.put(invoice);
        }
        Datastore.put(p);
        Datastore.put(ir);
        
    }
    
}
