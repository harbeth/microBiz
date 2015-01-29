package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.MiUserService;

// command controller for new/edit save
public abstract class InvoiceSaveActionController extends BaseController{

    protected InvoiceService invoiceService;
    protected CustomerService customerService;
    protected ContactService contactService;
    protected MiUserService userService;
    
    protected InvoiceMeta metaInvoice;
    
    public InvoiceSaveActionController(){
        super();
        invoiceService = new InvoiceService();
        customerService = new CustomerService();
        contactService = new ContactService();
        metaInvoice = new InvoiceMeta();
        userService = new MiUserService();
    }
    
    @Override
    public Navigation run() throws Exception {
        
        // save invoice for edit / new
        Invoice invoice = getInvoice();
        BeanUtil.copy(request, invoice); 
        
        if(asString("autoInvoiceNumber")!=null){
            invoice.setInvoiceNumber(MicroBizUtil.generateInvoiceNumber());
         }
        
        invoice.setSignDate();
        invoice.setPreferIntlDate();
        // set customer reference if has selection
        String customerKey = invoice.getCustomerKey();
        if ( !"-1".equals(customerKey) ) { 
            invoice.getCustomerRef().setModel(customerService
                          .get(Datastore.stringToKey(customerKey)));
        }
        // set contact reference if has selection
        String contactKey = invoice.getContactKey();
        if ( !"-1".equals(contactKey) ) { 
            invoice.getContactRef().setModel(contactService
                           .get(Datastore.stringToKey(invoice.getContactKey())));
        }
        
        invoice.getSalesRef().setModel(userService.get(Datastore.stringToKey(asString("sales"))));
        // should return key, if not, return to invoice list
        Key key = invoiceService.save(invoice);
        invoice.setKey(key);
        // keep on same tab, update info DIV
        requestScope("invoice", invoice);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
    
    public abstract Invoice getInvoice();
}
