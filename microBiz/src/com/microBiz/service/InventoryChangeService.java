package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.InventoryChangeMeta;
import com.microBiz.model.InventoryChange;


public class InventoryChangeService {
    private InventoryChangeMeta i = new InventoryChangeMeta();
 
    
    public List<InventoryChange> getAll() {
        return Datastore.query(i).asList();
    }


    public InventoryChange get(Key key) {
        return Datastore.get(i, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(InventoryChange f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    

}
