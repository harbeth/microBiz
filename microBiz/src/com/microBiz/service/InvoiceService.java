package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.meta.OrderMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;



public class InvoiceService {
    
    private InvoiceMeta i = new InvoiceMeta();
    private OrderMeta o = new OrderMeta();
 
    
    public List<Invoice> getAll() {
        return Datastore.query(i).asList();
    }
    
    public List<Invoice> getCustomerInvoice(Key customerKey) {
        return Datastore.query(i).asList();
    }
    
    public Order getInvoiceOrder(Key orderKey) {
        return Datastore.get(o, orderKey);
    }
    
    public Invoice get(Key key) {
        return Datastore.get(i, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Invoice save(Invoice i) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, i);
        tx.commit();
        return i;
    }
    

    

    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }


}
