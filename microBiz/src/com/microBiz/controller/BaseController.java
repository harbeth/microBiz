package com.microBiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;

import com.microBiz.MicroBizConst;



public abstract class BaseController extends Controller{
    protected List<String> roles ;
    protected List<String>  units ;
    protected List<String>  cxRatings;
    protected List<String>  prdTypes;
    protected List<String>  suppliers;
    protected List<String>  cxTypes;
    protected List<String>  txRates;
    protected List<String>  modules;
    protected List<String>  inventoryChangeTypes;
    protected List<String>  paymentTypes;
    protected List<String>  quoteStatus;
    protected List<String>  pymtTerms;
    
    public BaseController(){

        roles = new ArrayList<String>();
        units  = new ArrayList<String>();
        prdTypes  = new ArrayList<String>();
        cxRatings  = new ArrayList<String>();
        suppliers  = new ArrayList<String>();
        cxTypes  = new ArrayList<String>();
        txRates  = new ArrayList<String>();
        modules  = new ArrayList<String>();
        inventoryChangeTypes = new ArrayList<String>();
        paymentTypes = new ArrayList<String>();
        quoteStatus = new ArrayList<String>();
        pymtTerms = new ArrayList<String>();
  

        roles.add("admin"); // have full rights, 
        roles.add("installer"); // can only report job assinged to him
        roles.add("sales"); // can create quote, invoice, and see quote and invoice he created
        roles.add("manager"); // everything but configuration
        units.add("kg");
        units.add("pound");
        units.add("stroke");
        units.add("sq.ft.");
        cxRatings.add("normal");
        cxRatings.add("VIP");
        cxRatings.add("bad");
        prdTypes.add("selling");
        prdTypes.add("raw material");
        prdTypes.add("both");
        suppliers.add("");
        suppliers.add("polyfoam");
        suppliers.add("basf");
        cxTypes.add(MicroBizConst.CUSTOMER_TYPE_RESIDENTIAL);
        cxTypes.add(MicroBizConst.CUSTOMER_TYPE_COMMERCIAL);
        txRates.add("0.13");
        txRates.add("0");
        txRates.add("0.065");
        modules.add("customer");
        modules.add("invoice");
        modules.add("mgmt");
        modules.add("quote");
        modules.add("prd");
        modules.add("jobReport");
        inventoryChangeTypes.add("decrease");
        inventoryChangeTypes.add("increase");
        inventoryChangeTypes.add("reset");
        paymentTypes.add("check");
        paymentTypes.add("cash");
        paymentTypes.add("credit card");
        quoteStatus.add("open");
        quoteStatus.add("won");
        quoteStatus.add("failed");
        pymtTerms.add("0d");
        pymtTerms.add("30d");
        pymtTerms.add("45d");
    }
}
