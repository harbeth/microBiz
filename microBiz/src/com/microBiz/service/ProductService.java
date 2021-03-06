package com.microBiz.service;

import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.MicroBizConst;
import com.microBiz.meta.InventoryChangeMeta;
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
        return Datastore.query(p).filter(p.type.in(MicroBizConst.CODE_PRODUCT_TYPE_SELLING, MicroBizConst.CODE_PRODUCT_TYPE_BOTH)).asList();
    }
    
    public List<Product> getReportingPrds() {
        return Datastore.query(p).filter(p.type.in( MicroBizConst.CODE_PRODUCT_TYPE_BOTH, MicroBizConst.CODE_PRODUCT_TYPE_RAW_MATERIAL)).asList();
    }
    
    public Product get(Key key) {
        Product prd = Datastore.get(p, key);
        List<PrdRatio> prdRs = Datastore.query(PrdRatio.class,key).asList();
        if(prdRs!=null && prdRs.size()>0 ){
            prd.setPrdRatioList(prdRs);
        }
        
        return prd;
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

        }

        Datastore.put(tx, prdRs);
        tx.commit();
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
    
    public List<InventoryChange> getAllInventoryDetailsByProduct(Key ancestorKey) {
        InventoryChangeMeta icMeta = new InventoryChangeMeta();
        return Datastore.query(icMeta,ancestorKey)
                .sort(icMeta.createdAt.desc).asList();
    }

    public PrdRatio getPrdRatio(Key prdRatioKey) {
        return Datastore.get(pr, prdRatioKey);
    }
    
    public PrdRatio getPrdRatioByPrdAndDesc(Key prdKey, String desc) {
        List<PrdRatio> r = Datastore.query(pr,prdKey).filterInMemory(pr.desc.equal(desc)).asList();
        return r.get(0);
    }

}
