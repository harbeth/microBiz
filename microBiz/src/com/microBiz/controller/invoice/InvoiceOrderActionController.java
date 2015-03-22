package com.microBiz.controller.invoice;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.common.OrderLoadActionController;
import com.microBiz.model.Invoice;
import com.microBiz.model.Order;
import com.microBiz.service.InvoiceService;


public class InvoiceOrderActionController extends OrderLoadActionController {

    private InvoiceService invoiceService;

    public InvoiceOrderActionController(){
        super();
        invoiceService = new InvoiceService();
    }
    
    @Override
    public Navigation run() throws Exception {
        // get order key, discount, taxRate, total from UI
        Order order = getOrderData();
        Invoice invoice = invoiceService.get(Datastore.stringToKey(order.getInvoiceKey()));
        Key newOrderKey = orderService.updateOrder(order);
        invoice.getOrderRef().setKey(newOrderKey);
        invoice = invoiceService.save(invoice);
        setOrderData(order);
        // for the key
        requestScope("invoice", invoice);
        return forward("invoice-order.jsp");
    }
}
