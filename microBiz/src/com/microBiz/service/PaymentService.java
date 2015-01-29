package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.PaymentMeta;
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
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, p);
        tx.commit();
    }
    
}
