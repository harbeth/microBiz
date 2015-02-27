package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.meta.SupplierMeta;
import com.microBiz.model.MiUser;
import com.microBiz.model.Product;
import com.microBiz.model.Supplier;


public class SupplierService {
    private SupplierMeta p = new SupplierMeta();
 
    
    public List<Supplier> getAll() {
        return Datastore.query(p).asList();
    }
    
 
    public Supplier get(Key key) {
        return Datastore.get(p, key);
    }
    

    public void save(Supplier f) {

        Datastore.put(f);
     
    }
    
 

}
