package com.microBiz.controller.invoice;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.InvoiceOrder;
import com.microBiz.model.Order;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;

// command controller for new/edit save
public abstract class InvoiceSaveActionController extends OrderLoadActionController {

    protected InvoiceService invoiceService;
    protected CustomerService customerService;
    protected ContactService contactService;
    protected MiUserService userService;
    protected ProductService productService;
    
    protected InvoiceMeta metaInvoice;
    
    public InvoiceSaveActionController(){
        super();
        invoiceService = new InvoiceService();
        customerService = new CustomerService();
        contactService = new ContactService();
        metaInvoice = new InvoiceMeta();
        userService = new MiUserService();
        productService = new ProductService();
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
        Order order = getOrderData();
        // also reused by edit tab save function
        if ( order != null ) {
            // should return key, if not, return to invoice list
            Key key = invoiceService.saveNewInvoice(invoice, order);
            invoice = invoiceService.get(key);
            // get updated order
            List<InvoiceOrder> invoiceOrder = invoice.getInvoiceOrderRef().getModelList();
            // for invoice order data in items tab
            Order newOrder = invoiceOrder.get(0).getOrderRef().getModel();
            setOrderData(newOrder);
        }
        // keep on same tab, update info DIV
        requestScope("invoice", invoice);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
    
    public abstract Invoice getInvoice();
}
