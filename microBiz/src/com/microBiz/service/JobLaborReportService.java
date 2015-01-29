package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.JobLaborReportMeta;
import com.microBiz.model.Contact;
import com.microBiz.model.Customer;
import com.microBiz.model.JobLaborReport;

public class JobLaborReportService {
    
    private JobLaborReportMeta jlrMeta = new JobLaborReportMeta();
    
    public List<JobLaborReport> getAll() {
        return Datastore.query(jlrMeta).asList();
    }
    
    public JobLaborReport get(Key key) {
        return Datastore.get(jlrMeta, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(JobLaborReport jlr) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, jlr);
        tx.commit();
    }
    
    //???
    /*
    public void save(Key parentKey, Customer f) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(parentKey, Contact.class);
        f.setKey(childKey);
        Datastore.put(tx, f);
        tx.commit();
    }
    */
}
