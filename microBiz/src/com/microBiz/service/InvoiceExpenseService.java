package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InvoiceExpenseMeta;
import com.microBiz.model.InvoiceExpense;



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
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, e);
        tx.commit();
    }
    
    public void save(Key parentKey, InvoiceExpense expense) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, InvoiceExpense.class);
        expense.setKey(childKey);
        Datastore.put(tx, expense);
        tx.commit();
    }
}
