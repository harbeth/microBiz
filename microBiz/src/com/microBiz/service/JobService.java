package com.microBiz.service;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.microBiz.meta.JobMeta;
import com.microBiz.model.Job;
import com.microBiz.model.JobLaborReport;
import com.microBiz.model.JobMaterialReport;



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
    public void addJobMaterialReport(JobMaterialReport jmr, Key jobKey) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(jobKey, JobMaterialReport.class);
        jmr.setKey(childKey);
        jmr.setReportDate(new Date());
        Datastore.put(tx, jmr);
        tx.commit();
    }

    public void saveJobLaborReport(JobLaborReport jbr, Key jobKey) {
        Transaction tx = Datastore.beginTransaction();
        Key childKey = Datastore.allocateId(jobKey, JobLaborReport.class);
        jbr.setReportDate(new Date());
        jbr.setKey(childKey);
        Datastore.put(tx, jbr);
        tx.commit();
        
    }

    public List<JobMaterialReport> getJobMaterialReports(Key jobKey) {
        return Datastore.query(JobMaterialReport.class, jobKey).asList();
    }
    
    public List<JobLaborReport> getJobLaborReports(Key jobKey) {
        return Datastore.query(JobLaborReport.class, jobKey).asList();
    }

}
