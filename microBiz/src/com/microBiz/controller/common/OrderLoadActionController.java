package com.microBiz.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.microBiz.MicroBizCalculator;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Order;
import com.microBiz.model.OrderItem;
import com.microBiz.model.Product;
import com.microBiz.model.QuoteOrder;
import com.microBiz.service.OrderService;
import com.microBiz.service.ProductService;
import com.microBiz.service.QuoteService;

public abstract class OrderLoadActionController extends BaseController {
    
    protected ProductService productService;
    protected OrderService orderService;
    protected QuoteService quoteService;
    
    // common logic to set order and order item data and save 
    public OrderLoadActionController(){
        super();
        productService = new ProductService();
        orderService = new OrderService();
        quoteService = new QuoteService();
    }
    
    @Override
    public Navigation run() throws Exception {
        return null;
    }
    
    public void setOrderData(Order order) throws Exception {
        List<OrderItem> items = orderService.gerOrderItems(order.getKey());
        // set order sub total
        order.setSubTotal(MicroBizCalculator.getSubTotal(items));
        BeanUtil.copy(order, request);
        requestScope("orderItems", items);
        
        List<Product> prodcutList = productService.getSellingPrds();
        requestScope("products", prodcutList);
        requestScope("txRates", txRates);
    }
    
    public void setOrderFromList(List<QuoteOrder> quoteOrderList) throws Exception {
        // get first quote version  quoteOrderList.size() should > 0
        QuoteOrder firstQuoteOrder = quoteOrderList.get(0);
        QuoteOrder quoteOrder = quoteService.getQuoteOrder(firstQuoteOrder.getKey());
        Order order = quoteOrder.getOrderRef().getModel();
        setOrderData(order);
        requestScope("quoteOrder", quoteOrder);
    }
    
    public Order getOrderData() {
        // assemble quote item
        String[] items = paramValues("items");
        // reused by save on edit tab in details page, 
        Order order = null;
        if ( items != null ) {
            // get order key, discount, taxRate, total from UI
            order = new Order();
            BeanUtil.copy(request, order);
            //  need reuse with createAction
            
            String[] rates = paramValues("rates");
            String[] descs = paramValues("descs");
            String[] quantities = paramValues("qtys");
            // product should not be empty, remove -1 at the end
            int arrLength = items.length - 1;
            List<OrderItem> oiList = new ArrayList<OrderItem>();
            for ( int i = 0 ; i < arrLength; i ++ ) {
                // get product REF
                Product product = productService.get(Datastore.stringToKey(items[i]));
                OrderItem oi = new OrderItem();
                oi.getProductRef().setModel(product);
                oi.setDesc(descs[i]);
                oi.setRate(Double.valueOf(rates[i]));
                oi.setQty(Double.valueOf(quantities[i]));
                oiList.add(oi);
            }
            order.setOrderItemList(oiList);
        }
        return order;
    }
}
