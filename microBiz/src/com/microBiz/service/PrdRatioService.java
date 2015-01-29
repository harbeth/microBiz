package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

import com.microBiz.meta.PrdRatioMeta;

import com.microBiz.model.PrdRatio;


public class PrdRatioService {
    private PrdRatioMeta p = new PrdRatioMeta();
 
    
    public List<PrdRatio> getAll() {
        return Datastore.query(p).asList();
    }
    
    public PrdRatio get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(PrdRatio f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void save(Key parentKey, PrdRatio f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, PrdRatio.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

}
