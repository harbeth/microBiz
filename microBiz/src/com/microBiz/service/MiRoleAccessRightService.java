package com.microBiz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.MiRoleAccessRightMeta;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.MiRoleAccessRight;
import com.microBiz.model.MiUser;
import com.microBiz.model.Product;


public class MiRoleAccessRightService {
    private MiRoleAccessRightMeta p = new MiRoleAccessRightMeta();
 
    
    public List<MiRoleAccessRight> getAll() {
        return Datastore.query(p).asList();
    }
    
    public String getAccessibleModuleByRole(String role) {
        List<MiRoleAccessRight> rights = Datastore.query(p).filter(p.role.equal(role)).asList();
        if(rights.size() == 0){
            return null;
        }else{
            StringBuffer result = new StringBuffer();
            Iterator<MiRoleAccessRight> i = rights.iterator();
            while (i.hasNext()){
                MiRoleAccessRight right = (MiRoleAccessRight)i.next();
                result.append(right.getAccessibleModule()+",");
            }
            return result.toString();
        }
    }
    
    public MiRoleAccessRight get(Key key) {
        return Datastore.get(p, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(MiRoleAccessRight f) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, f);
        tx.commit();
    }
    

}
