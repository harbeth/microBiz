package com.microBiz.controller.common;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizConst;
import com.microBiz.controller.BaseController;
import com.microBiz.controller.VelocityHelper;
import com.microBiz.meta.JobMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Job;
import com.microBiz.model.MiUser;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.JobService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.OrderService;
import com.microBiz.service.ProductService;


// from AJAX call, on the submit form of jab details form
public abstract class JobEditActionCommonController extends BaseController {

    protected JobService jobService;
    protected InvoiceService invoiceService;
    protected MiUserService userService;
    protected OrderService orderService;
    protected ProductService prdService;
    protected JobMeta metaJob;
    
    public JobEditActionCommonController(){
        super();
        jobService = new JobService();
        invoiceService = new InvoiceService();
        userService = new MiUserService();
        orderService = new OrderService();
        prdService = new ProductService();
        metaJob = new JobMeta();
    }
    
    @Override
    public Navigation run() throws Exception {
        return null;
    }
    
    protected Invoice saveJob() {
        Job job = new Job();
        if ( asKey(metaJob.key) != null ) {
            job = jobService.get(asKey(metaJob.key));
        }else{
            job = new Job();
        }

        BeanUtil.copy(request, job);
        Key invoiceKey = Datastore.stringToKey(job.getInvoiceKey());
        Invoice invoice = invoiceService.get(invoiceKey);
        job.setStartingDate();
        job.getInvoiceRef().setModel(invoice);
   
        
        String[] prds = paramValues("prds");
        if(prds!=null && prds.length>0){
            List<String> prdKeys = new ArrayList<String>(prds.length);
            Collections.addAll(prdKeys, prds);
            job.setUsePrdKeys(prdKeys);
        }
        
        
        
        if(job.getKey()==null && job.getStatus()==MicroBizConst.CODE_STATUS_OPEN){
            informInstallerByEmail(job,invoice);
        }
        jobService.save(job);
        return invoice;
    }
    
    private void informInstallerByEmail(Job job,Invoice invoice ){
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        VelocityContext context = new VelocityContext();
        context.put("invoice_number", invoice.getInvoiceNumber());
        context.put("address", invoice.getAddress());
        context.put("start_date", job.getStartingDateStr());
        context.put("note", job.getNote());
        StringBuffer details = new StringBuffer();
        List<OrderItem> items = orderService.gerOrderItems(invoice.getOrderRef().getKey());
        Iterator<OrderItem> i = items.iterator();
        while (i.hasNext()){
            OrderItem item = i.next();
            details.append(item.getProductRef().getModel().getModel())
            .append(":" ).append(item.getDesc()).append("\n");
        }
        context.put("details", details.toString());
        
        StringBuffer prds = new StringBuffer();
        List<String> prdKeys = job.getUsePrdKeys();
        Iterator<String> pi = prdKeys.iterator();
        while(pi.hasNext()){
            Product prd = prdService.get(Datastore.stringToKey((String)pi.next()));
            prds.append(prd.getModel()).append(", ");
        }
        int prdslength = prds.length();
        prds.delete(prdslength-2, prdslength);
        context.put("prd_to_use", prds.toString());
        
        VelocityEngine ve = VelocityHelper.getVelocityEngine();
        // Finds template in WEB-INF/classes
        Template template = ve.getTemplate("emailNewJobTemplate.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String message = writer.toString();
        System.out.println("message is " + message);
        MiUser installer = userService.getUserByName(job.getInstaller());
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@heleadsys.appspotmail.com ", "New Job Alert"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(installer.getEmail(), "test"));
            msg.setSubject("New Job on: " + job.getStartingDateStr());
            msg.setContent(message, "text/html" );
            Transport.send(msg);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            
        }
    }
}
