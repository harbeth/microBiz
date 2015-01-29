package com.microBiz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slim3.controller.Controller;
import org.apache.commons.lang.RandomStringUtils;

import com.microBiz.MicroBizConst;



public abstract class BaseController extends Controller{
    protected List roles ;
    protected List units ;
    protected List cxRatings;
    protected List prdTypes;
    protected List suppliers;
    protected List cxTypes;
    protected List txRates;
    protected List modules;
    protected List inventoryChangeTypes;
    protected List paymentTypes;
    
    public BaseController(){

        roles = new ArrayList();
        units  = new ArrayList();
        prdTypes  = new ArrayList();
        cxRatings  = new ArrayList();
        suppliers  = new ArrayList();
        cxTypes  = new ArrayList();
        txRates  = new ArrayList();
        modules  = new ArrayList();
        inventoryChangeTypes = new ArrayList();
        paymentTypes = new ArrayList();


        roles.add("admin"); // have full rights, 
        roles.add("installer"); // can only report job assinged to him
        roles.add("sales"); // can create quote, invoice, and see quote and invoice he created
        roles.add("manager"); // everything but configuration
        units.add("");
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
        inventoryChangeTypes.add("decrease");
        inventoryChangeTypes.add("increase");
        inventoryChangeTypes.add("reset");
        paymentTypes.add("check");
        paymentTypes.add("cash");
        paymentTypes.add("credit card");
        
        


        
    }
    

 
    
    
    public List getRoles() {
        return roles;
    }


    public void setRoles(List roles) {
        this.roles = roles;
    }


    public List getUnits() {
        return units;
    }


    public void setUnits(List units) {
        this.units = units;
    }


    public List getCxRatings() {
        return cxRatings;
    }


    public void setCxRatings(List cxRatings) {
        this.cxRatings = cxRatings;
    }


    public List getPrdTypes() {
        return prdTypes;
    }


    public void setPrdTypes(List prdTypes) {
        this.prdTypes = prdTypes;
    }
    
    public List getSuppliers() {
        return suppliers;
    }


    public void setSuppliers(List suppliers) {
        this.suppliers = suppliers;
    }


    public List getCxTypes() {
        return cxTypes;
    }


    public void setCxTypes(List cxTypes) {
        this.cxTypes = cxTypes;
    }


    public List getTxRates() {
        return txRates;
    }


    public void setTxRates(List txRates) {
        this.txRates = txRates;
    }
    
}
