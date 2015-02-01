package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.PrdRatioMeta;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.InventoryChange;
import com.microBiz.model.PrdRatio;
import com.microBiz.model.Product;


public class ProductService {
    private ProductMeta p = new ProductMeta();
    // prdRation is child of prd, to be able to make them in the same transaction
    private PrdRatioMeta pr = new PrdRatioMeta();
 
    
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
    
    //when save prdRatios for a prd, delete the original prdRatios first, then insert new prdRatios, with prd as parent
    public void save(Product parent, List<PrdRatio> prdRs) {
        Transaction tx = Datastore.beginTransaction();
        Key parentKey = null;
        if(parent.getKey()!=null){
            parentKey = parent.getKey();
            Datastore.deleteAll(tx,parentKey);
            Datastore.put(tx, parent);
        }else{
            parentKey = Datastore.put(tx, parent);
        }
        
        Iterator<PrdRatio> prdRsI = prdRs.iterator();

        while(prdRsI.hasNext()) {
            PrdRatio prdR = (PrdRatio)prdRsI.next();
            Key childKey = Datastore.allocateId(parentKey, PrdRatio.class);
            prdR.setKey(childKey);
            prdR.getProductRef().setModel(parent);
        }

        Datastore.put(tx, prdRs);
        
    }

    public void save(Product p, InventoryChange ic) {
        Transaction tx = Datastore.beginTransaction();
        ic.getProductRef().setModel(p);
        Datastore.put(tx, p);
        Key childKey = Datastore.allocateId(p.getKey(), InventoryChange.class);
        ic.setKey(childKey);
        Datastore.put(tx, ic);
        tx.commit();
        
    }

}
