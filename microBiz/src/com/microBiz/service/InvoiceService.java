package com.microBiz.service;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.meta.OrderMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceReport;
import com.microBiz.model.Order;

public class InvoiceService {
    
    private InvoiceMeta i = new InvoiceMeta();
    private OrderMeta o = new OrderMeta();
 
    
    public List<Invoice> getAll() {
        return Datastore.query(i).asList();
    }
    

    
    public List<Invoice> getInvoicesByCreatorName(String name) {
        return Datastore.query(i).filter(i.creatorName.equal(name)).asList();
    }

    public Order getInvoiceOrder(Key orderKey) {
        return Datastore.get(o, orderKey);
    }
    
    public Invoice get(Key key) {
        return Datastore.get(i, key);
    }
    
    public void delete(Key key) {
        Datastore.delete(key);
    }

    public Invoice save(Invoice i) {
        
        if(i.getInvoiceReportRef()==null || i.getInvoiceReportRef().getModel()==null){
            InvoiceReport ir = new InvoiceReport();
            ir.setTotal(i.getOrderRef().getModel().getTotal());
            Datastore.put(ir);
            i.getInvoiceReportRef().setModel(ir);
        }
        
         
        if(i.getKey()!=null){
            Invoice oldInvoice = get(i.getKey());
            if(oldInvoice.getStatus()!=i.getStatus()){ 
                i.setStatusChangeDate(new Date());
            }
        }
        Key key = Datastore.put(i);
        i.setKey(key);
        return i;
    }

    public List<Invoice> getByInvoiceNumber(String invoiceNumber) {
        return Datastore.query(i).filter(i.invoiceNumber.equal(invoiceNumber)).asList();
    }



    public List<Invoice> getInvoicesForSalesCommission(String salesName) {
        return Datastore.query(i).filter(i.sales.equal(salesName),
            i.status.equal(MicroBizConst.CODE_STATUS_CLOSED), i.paidOff.equal(true),
            i.salesPaid.notEqual(true)).asList();
    }
    
    public List<Invoice> getUnPaidOffInvoices() {
        return Datastore.query(i).filter(i.status.equal(MicroBizConst.CODE_STATUS_CLOSED), 
            i.paidOff.notEqual(true)).asList();
    }
    
    public List<Invoice> getOpenInvoices() {
        return Datastore.query(i).filter(i.status.equal(MicroBizConst.CODE_STATUS_OPEN)).asList();
    }



    public void saveInvoiceReport(InvoiceReport ir) {
        Datastore.put(ir);
        
    }
}
