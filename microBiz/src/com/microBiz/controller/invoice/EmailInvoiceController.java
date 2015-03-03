package com.microBiz.controller.invoice;

import java.io.StringWriter;
import java.util.Date;
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

import com.microBiz.controller.BaseController;
import com.microBiz.controller.VelocityHelper;
import com.microBiz.model.Invoice;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.OrderService;


public class EmailInvoiceController extends BaseController {
    private InvoiceService invoiceService;
    
    public EmailInvoiceController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    
    @Override
    public Navigation run() throws Exception {
        Invoice invoice = invoiceService.get(Datastore.stringToKey(asString("invoiceKey"))); 
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        VelocityContext context = new VelocityContext();
        context.put("name", invoice.getCustomerRef().getModel().getName());
        context.put("invoice_number", invoice.getInvoiceNumber());
        context.put("amount", invoice.getOrderRef().getModel().getTotal().toString());
        String link = "<a href=http://localhost:8888/pub/emailInvoicePdf?invoicekey"
        +Datastore.keyToString(invoice.getKey())+">here</a>";
        context.put("link", link);
        VelocityEngine ve = VelocityHelper.getVelocityEngine();
        // Finds template in WEB-INF/classes
        Template template = ve.getTemplate("emailInvoiceTemplate.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String message = writer.toString();
        System.out.println("message is " + message);
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@heleadsys.appspotmail.com ", "Invoice From Foam Expert"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(invoice.getCustomerRef().getModel().getEmail(), "test"));
            msg.setSubject("Invoice for Spray Foam project at " + invoice.getAddress());
            msg.setContent(message, "text/html" );
            Transport.send(msg);

        } catch (Exception e) {
            
        }
        writer.flush();
        writer.close();
        //return "Have successfully send email to " + invoice.getCustomerRef().getModel().getName();
        return null;
    }
}
