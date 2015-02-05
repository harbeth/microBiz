package com.microBiz.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.JobMeta;
import com.microBiz.model.Job;



public class JobService {
    
    private JobMeta job = new JobMeta();
    
    public List<Job> getAll() {
        return Datastore.query(job).asList();
    }
    
    public Job get(Key key) {
        return Datastore.get(job, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public void save(Job job) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tx, job);
        tx.commit();
    }

    public List<Job> getAllUncompleteJobs() {
        return Datastore.query(job).filter(job.status.notEqual("complete")).asList();
    
    }

}
