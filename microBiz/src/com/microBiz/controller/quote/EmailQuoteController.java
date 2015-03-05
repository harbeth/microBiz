package com.microBiz.controller.quote;

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
import com.microBiz.model.Quote;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.OrderService;
import com.microBiz.service.QuoteService;


public class EmailQuoteController extends BaseController {
    private QuoteService quoteService;
    
    public EmailQuoteController(){
        super();
        quoteService = new QuoteService();
    }
    
    
    @Override
    public Navigation run() throws Exception {
        Quote quote = quoteService.get(Datastore.stringToKey(asString("quoteKey")));
        QuoteOrder qo = quote.getQuoteOrderRef().getModelList().get(0);
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        VelocityContext context = new VelocityContext();
        context.put("name", quote.getCustomerRef().getModel().getName());
        context.put("address", quote.getAddress());
        context.put("amount", qo.getOrderRef().getModel().getTotal().toString());
        String link = "<a href=http://localhost:8888/pub/quoteToPdf?quoteKey="
        +Datastore.keyToString(quote.getKey())+">here</a>";
        context.put("link", link);
        VelocityEngine ve = VelocityHelper.getVelocityEngine();
        // Finds template in WEB-INF/classes
        Template template = ve.getTemplate("emailQuoteTemplate.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String message = writer.toString();
        System.out.println("message is " + message);
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@heleadsys.appspotmail.com ", "Quotation From Foam Expert"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(quote.getCustomerRef().getModel().getEmail(), "test"));
            msg.setSubject("Quotation for Spray Foam project at " + quote.getAddress());
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
