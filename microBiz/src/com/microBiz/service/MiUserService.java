package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.model.MiUser;
import com.microBiz.model.Product;


public class MiUserService {
    private MiUserMeta p = new MiUserMeta();
 
    
    public List<MiUser> getAll() {
        return Datastore.query(p).asList();
    }
    
    public List<MiUser> getSales() {
        return Datastore.query(p).filter(p.miRole.in("manager","sales")).asList();
    }
    
    public List<MiUser> getInstallers() {
        return Datastore.query(p).filter(p.miRole.equal("installer")).asList();
    }
    
    public MiUser getUserByEmail(String email) {
        List<MiUser> result = Datastore.query(p).filter(p.email.equal(email.toLowerCase().trim())).asList();
        if(result.size() == 0){
            return null;
        }else{
            return (MiUser)result.get(0);
        }
    }
    
    public MiUser get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(MiUser f) {
        Transaction tx = Datastore.beginTransaction();
        f.setEmail(f.getEmail().toLowerCase());
        Datastore.put(tx, f);
        tx.commit();
    }
    
    public void save(Key parentKey, MiUser f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Product.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }

    public MiUser getUserByName(String installer) {
        List<MiUser> result = Datastore.query(p).filter(p.name.equal(installer)).asList();
        if(result.size() == 0){
            return null;
        }else{
            return (MiUser)result.get(0);
        }
    }

    public List<String> getSalesNames() {
       
        return getNames(Datastore.query(p).filter(p.miRole.in("manager","sales")).asList());

    }

    public List<String> getInstallerNames() {
        return getNames(Datastore.query(p).filter(p.miRole.equal("installer")).asList());
    }
    
    private List<String> getNames(List<MiUser> users) {
        List<String> result = new ArrayList<String>();

        Iterator i = users.iterator();
        while(i.hasNext()){
            MiUser u = (MiUser)i.next();
            result.add(u.getName());
        }
        
        return result;
    }

}
