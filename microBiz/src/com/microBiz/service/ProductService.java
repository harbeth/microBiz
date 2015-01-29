package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.Product;


public class ProductService {
    private ProductMeta p = new ProductMeta();
 
    
    public List<Product> getAll() {
        return Datastore.query(p).asList();
    }
    
    public List<Product> getSellingPrds() {
        return Datastore.query(p).filter(p.type.in("selling", "both")).asList();
    }
    
    public List<Product> getReportingPrds() {
        return Datastore.query(p).filter(p.type.in("both", "raw material")).asList();
    }
    
    public Product get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(Product f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void save(Key parentKey, Product f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Product.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

}
