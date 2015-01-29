package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;



public class InvoiceService {
    
    private InvoiceMeta i = new InvoiceMeta();
 
    
    public List<Invoice> getAll() {
        return Datastore.query(i).asList();
    }
    
    public List<Invoice> getCustomerInvoice(Key customerKey) {
        return Datastore.query(i).asList();
    }
    
    public Invoice get(Key key) {
        return Datastore.get(i, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Invoice i) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, i);
        tx.commit();
        return key;
    }
    
    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }
}
