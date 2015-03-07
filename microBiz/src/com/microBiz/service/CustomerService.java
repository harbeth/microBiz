package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Customer;

public class CustomerService {
    
    private CustomerMeta p = new CustomerMeta();
    
    public List<Customer> getAll() {
        return Datastore.query(p).asList();
    }
    
    public List<Customer> searchStartWith(String searchStr) {
        // case insensitive ??
        return Datastore.query(p).filter(p.name.startsWith(searchStr)).asList();
    }
    
    public Customer get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key key = Datastore.put(tx, f);
        tx.commit();
        return key;
    }
    
    public void save(Key parentKey, Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Customer.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

}
