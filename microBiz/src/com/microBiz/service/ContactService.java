package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.ContactMeta;
import com.microBiz.meta.CustomerMeta;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;



public class ContactService {
    private ContactMeta p = new ContactMeta();
 
    //test for git check in
    public List<Contact> getAll() {
        return Datastore.query(p).asList();
    }
    
    public Contact get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(Contact f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void save(Key parentKey, Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Contact.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

}
