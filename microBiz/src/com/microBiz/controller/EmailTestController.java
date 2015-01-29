package com.microBiz.controller;

import java.io.BufferedOutputStream;
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
import org.slim3.util.ResponseLocator;

import com.google.appengine.api.datastore.KeyFactory;


public class EmailTestController extends BaseController {

    @Override
    public Navigation run() throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        VelocityContext context = new VelocityContext();
        context.put("date", new Date());
        context.put("name", "johndoe");
        VelocityEngine ve = VelocityHelper.getVelocityEngine();
        // Finds template in WEB-INF/classes
        Template template = ve.getTemplate("emailTemplate.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String message = writer.toString();
        
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@heleadsys.appspotmail.com ", "HE Lead Alert"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("test@example.ca", "test"));
            msg.setSubject("test");
            msg.setContent(message, "text/html" );
            Transport.send(msg);

        } catch (Exception e) {
            
        }
        writer.flush();
        writer.close();
        return null;
    }
}
