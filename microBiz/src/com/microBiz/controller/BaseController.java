package com.microBiz.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slim3.controller.Controller;

import com.microBiz.MicroBizConst;
import com.microBiz.MicroBizUtil;
import com.microBiz.PropertyHelper;
import com.microBiz.model.LabelValue;



public abstract class BaseController extends Controller {
    
    protected List<String> roles ;
    protected List<String>  txRates;
    protected List<String>  modules;
    
    /*
    protected List<String>  units ;
    protected List<String>  cxRatings;
    protected List<String>  prdTypes;
    protected List<String>  suppliers;
    protected List<String>  cxTypes;
    
    protected List<String>  inventoryChangeTypes;
    protected List<String>  paymentTypes;
    protected List<String>  quoteStatus;
    protected List<String>  pymtTerms;
    */
    
    protected List<LabelValue>  units ;
    protected List<LabelValue>  cxRatings;
    protected List<LabelValue>  prdTypes;
    protected List<LabelValue>  suppliers;
    protected List<LabelValue>  cxTypes;
    
    protected List<LabelValue>  inventoryChangeTypes;
    protected List<LabelValue>  paymentTypes;
    protected List<LabelValue>  quoteStatus;
    protected List<LabelValue>  pymtTerms;
    
    protected PropertyHelper proHelper;
    
    public BaseController(){
        proHelper = new PropertyHelper();
        populateDropdownList();
    }
    
    /*
    public void BaseController11(){

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
    */
    
    private void populateDropdownList(){
        loadLegacyDropDown();       
        List<String> roleKeyList = new ArrayList<String>();
        roleKeyList.add(MicroBizConst.CODE_ROLE_ADMIN);
        roleKeyList.add(MicroBizConst.CODE_ROLE_INSTALLER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_MANAGER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_SALES);
        getDropDownList(roleKeyList);
        
        List<String> unitKeyList = new ArrayList<String>();
        unitKeyList.add(MicroBizConst.CODE_UNIT_KG);
        unitKeyList.add(MicroBizConst.CODE_UNIT_POUND);
        unitKeyList.add(MicroBizConst.CODE_UNIT_STROKE);
        unitKeyList.add(MicroBizConst.CODE_UNIT_SQFT);
        units = getDropDownList(unitKeyList);
        
        List<String> cxRatingList = new ArrayList<String>();
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_BAD);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_NORMAL);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_VIP);
        cxRatings = getDropDownList(cxRatingList);
        
        List<String> productionTypeList = new ArrayList<String>();
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_SELLING);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_RAW_MATERIAL);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_BOTH);
        prdTypes = getDropDownList(productionTypeList);
        
        List<String> supplierList = new ArrayList<String>();
        supplierList.add(MicroBizConst.CODE_SUPPLIER_PLOYFOAM);
        supplierList.add(MicroBizConst.CODE_SUPPLIER_BASF);
        suppliers = getDropDownList(supplierList);
        
        List<String> cxTypeList = new ArrayList<String>();
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_RESIDENTIAL);
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_COMMERCIAL);
        cxTypes = getDropDownList(cxTypeList);
        
        List<String> inventoryChangeTypeList = new ArrayList<String>();
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_INCR);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_DESC);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_RESET);
        inventoryChangeTypes = getDropDownList(inventoryChangeTypeList);
        
        List<String> paymentTypeList = new ArrayList<String>();
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CASH);
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CHECK);
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CREDIT_CARD);
        paymentTypes = getDropDownList(paymentTypeList);
        
        List<String> quoteStatusList = new ArrayList<String>();
        quoteStatusList.add(MicroBizConst.CODE_STATUS_OPEN);
        quoteStatusList.add(MicroBizConst.CODE_STATUS_WON);
        quoteStatusList.add(MicroBizConst.CODE_STATUS_FAILED);
        quoteStatus = getDropDownList(quoteStatusList);
        
        List<String> paymentTermsList = new ArrayList<String>();
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_0D);
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_30D);
        paymentTermsList.add(MicroBizConst.CODE_PAYMENT_TERMS_45D);
        pymtTerms = getDropDownList(paymentTermsList);
        
    }
    

    
    private void loadLegacyDropDown() {
        roles = new ArrayList<String>();
        txRates  = new ArrayList<String>();
        modules  = new ArrayList<String>();
        
        roles.add("admin"); // have full rights, 
        roles.add("installer"); // can only report job assinged to him
        roles.add("sales"); // can create quote, invoice, and see quote and invoice he created
        roles.add("manager"); // everything but configuration
        
        modules.add("customer");
        modules.add("invoice");
        modules.add("mgmt");
        modules.add("quote");
        modules.add("prd");
        modules.add("jobReport");
        modules.add("manager");
        
        txRates.add("0.13");
        txRates.add("0");
        txRates.add("0.065");
    }
    
  
    private List<LabelValue> getDropDownList(List<String> keyList) {
        List<LabelValue> lvList = new ArrayList<LabelValue>();
       
        for ( String key : keyList ) {
            String label = proHelper.getConstantLable(key);
            LabelValue lv = new LabelValue(label, Integer.valueOf(key));
            lvList.add(lv);
        }
        return lvList;
    }
}
