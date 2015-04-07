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
    
    public List<Customer> getActiveCustomers() {
        return Datastore.query(p).filter(p.active.equal("on")).asList();
    }
    
    public List<Customer> searchActiveCustomerStartWith(String searchStr) {
        // case insensitive ??
        return Datastore.query(p).filter(p.searchName.startsWith(searchStr.trim().toLowerCase()),p.active.equal("on")).asList();
    }
    
    public List<Customer> searchStartWith(String searchStr) {
        // case insensitive ??
        return Datastore.query(p).filter(p.searchName.startsWith(searchStr.trim().toLowerCase())).asList();
    }
    
    public List<Customer> getByCustomerName(String name) {
        // case insensitive ??
        return Datastore.query(p).filter(p.searchName.equal(name.trim().toLowerCase())).asList();
    }
    
    public Customer get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Key save(Customer f) {
        Transaction tx = Datastore.beginTransaction();
        f.setSearchName(f.getName().trim().toLowerCase());
        Key key = Datastore.put(tx, f);
        tx.commit();
        return key;
    }
    


}
