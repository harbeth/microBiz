package com.microBiz.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slim3.controller.Controller;

import com.microBiz.MicroBizConst;
import com.microBiz.model.LabelValue;



public abstract class BaseController extends Controller {
    
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
    
    private void loadProperties() {
        Properties prop = new Properties();
        try {
            // microBiz.properties
            InputStream in = getClass().getResourceAsStream("/microBiz.properties");
            prop.load(in);
        }catch(Exception e) {
            System.out.println("cannot find file: " + e.getMessage());
        }
    }
    
    private void populateProperty(Properties prop) {
        List<String> roleKeyList = new ArrayList<String>();
        roleKeyList.add(MicroBizConst.CODE_ROLE_ADMIN);
        roleKeyList.add(MicroBizConst.CODE_ROLE_INSTALLER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_MANAGER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_SALES);
        getDropDownList(prop, roleKeyList);
        
        List<String> unitKeyList = new ArrayList<String>();
        unitKeyList.add(MicroBizConst.CODE_UNIT_KG);
        unitKeyList.add(MicroBizConst.CODE_UNIT_POUND);
        unitKeyList.add(MicroBizConst.CODE_UNIT_STROKE);
        unitKeyList.add(MicroBizConst.CODE_UNIT_SQFT);
        getDropDownList(prop, unitKeyList);
        
        List<String> cxRatingList = new ArrayList<String>();
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_BAD);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_NORMAL);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_VIP);
        getDropDownList(prop, cxRatingList);
        
        List<String> productionTypeList = new ArrayList<String>();
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_SELLING);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_RAW_MATERIAL);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_BOTH);
        getDropDownList(prop, productionTypeList);
        
        List<String> supplierList = new ArrayList<String>();
        supplierList.add(MicroBizConst.CODE_SUPPLIER_PLOYFOAM);
        supplierList.add(MicroBizConst.CODE_SUPPLIER_BASF);
        getDropDownList(prop, supplierList);
        
        List<String> cxTypeList = new ArrayList<String>();
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_RESIDENTIAL);
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_COMMERCIAL);
        getDropDownList(prop, cxTypeList);
        
        List<String> inventoryChangeTypeList = new ArrayList<String>();
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_INCR);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_DESC);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_RESET);
        getDropDownList(prop, inventoryChangeTypeList);
        
        List<String> paymentTypeList = new ArrayList<String>();
        inventoryChangeTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CASH);
        inventoryChangeTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CHECK);
        inventoryChangeTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CREDIT_CARD);
        getDropDownList(prop, paymentTypeList);
        
        List<String> quoteStatusList = new ArrayList<String>();
        quoteStatusList.add(MicroBizConst.CODE_QUOTE_STATUS_OPEN);
        quoteStatusList.add(MicroBizConst.CODE_QUOTE_STATUS_WON);
        quoteStatusList.add(MicroBizConst.CODE_QUOTE_STATUS_FAILED);
        getDropDownList(prop, quoteStatusList);
        
        List<String> paymentTermsList = new ArrayList<String>();
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_0D);
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_30D);
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_45D);
        getDropDownList(prop, paymentTermsList);
    }
    
    private List<LabelValue> getDropDownList(Properties prop, List<String> keyList) {
        List<LabelValue> lvList = new ArrayList<LabelValue>();
        for ( String key : keyList ) {
            String label = prop.getProperty(key);
            LabelValue lv = new LabelValue(label, Integer.valueOf(key));
            lvList.add(lv);
        }
        return lvList;
    }
}
