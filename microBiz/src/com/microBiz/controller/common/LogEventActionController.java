package com.microBiz.controller.common;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.microBiz.controller.BaseController;
import com.microBiz.model.Payment;
import com.microBiz.model.MiLog;
import com.microBiz.service.InvoiceService;
import com.microBiz.service.PaymentService;
import com.microBiz.service.QuoteService;

// from the link to refresh whole page

public class LogEventActionController extends LogEventController {

    @Override
    protected boolean forAction(){
        return true;
    }
 
}
