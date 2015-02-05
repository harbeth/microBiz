package com.microBiz.controller.invoice;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.MicroBizUtil;
import com.microBiz.controller.BaseController;
import com.microBiz.meta.InvoiceMeta;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.service.ContactService;
import com.microBiz.service.CustomerService;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;

// command controller for new/edit save
public abstract class InvoiceSaveActionController extends BaseController{

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
        
        Order order = new Order();
        
        order.setTaxRate(asDouble("taxRate"));
        order.setDiscount(asDouble("discount"));
        order.setTotal(asDouble("total"));
        // remove -1 for product
        String[] items = paramValues("items");
        String[] rates = paramValues("rates");
        String[] descs = paramValues("descs");
        String[] quantities = paramValues("qtys");
        // product should not be empty
        int arrLength = items.length - 1;
        List<OrderItem> qiList = new ArrayList<OrderItem>();
        for ( int i = 0 ; i < arrLength; i ++ ) {
            if(rates[i]!=null && !rates[i].equals("")){
            Product product = productService.get(Datastore.stringToKey(items[i]));
            OrderItem qi = new OrderItem();
            qi.getProductRef().setModel(product);
            qi.setDesc(descs[i]);
            qi.setRate(Double.valueOf(rates[i]));
            qi.setQty(Double.valueOf(quantities[i]));
  
            qiList.add(qi);
            }
        }

        // should return key, if not, return to invoice list
        Key key = invoiceService.saveNewInvoice(invoice, order, qiList);
        
        invoice.setKey(key);
        // keep on same tab, update info DIV
        requestScope("invoice", invoice);
        return forward(getReturnJsp());
    }
    
    public abstract String getReturnJsp();
    
    public abstract Invoice getInvoice();
}
