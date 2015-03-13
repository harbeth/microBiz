package com.microBiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;

import com.microBiz.MicroBizConst;
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
    protected List<LabelValue>  cxTypes;
    
    protected List<LabelValue>  inventoryChangeTypes;
    protected List<LabelValue>  paymentTypes;
    protected List<LabelValue>  quoteStatus;
    protected List<LabelValue>  jobStatus;
    protected List<LabelValue>  invoiceStatus;
    protected List<LabelValue>  pymtTerms;
    
  
    public BaseController(){

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
        List<Integer> roleKeyList = new ArrayList<Integer>();
        roleKeyList.add(MicroBizConst.CODE_ROLE_ADMIN);
        roleKeyList.add(MicroBizConst.CODE_ROLE_INSTALLER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_MANAGER);
        roleKeyList.add(MicroBizConst.CODE_ROLE_SALES);
        getDropDownList(roleKeyList);
        
        List<Integer> unitKeyList = new ArrayList<Integer>();
        unitKeyList.add(MicroBizConst.CODE_UNIT_KG);
        unitKeyList.add(MicroBizConst.CODE_UNIT_STROKE);
        unitKeyList.add(MicroBizConst.CODE_UNIT_SQFT);
        units = getDropDownList(unitKeyList);
        
        List<Integer> cxRatingList = new ArrayList<Integer>();

        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_NORMAL);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_BAD);
        cxRatingList.add(MicroBizConst.CODE_CUSTOMER_RATING_VIP);
        cxRatings = getDropDownList(cxRatingList);
        
        List<Integer> productionTypeList = new ArrayList<Integer>();
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_SELLING);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_RAW_MATERIAL);
        productionTypeList.add(MicroBizConst.CODE_PRODUCT_TYPE_BOTH);
        prdTypes = getDropDownList(productionTypeList);

        
        List<Integer> cxTypeList = new ArrayList<Integer>();
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_RESIDENTIAL);
        cxTypeList.add(MicroBizConst.CODE_CUSTOMER_TYPE_COMMERCIAL);
        cxTypes = getDropDownList(cxTypeList);
        
        List<Integer> inventoryChangeTypeList = new ArrayList<Integer>();
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_INCR);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_DESC);
        inventoryChangeTypeList.add(MicroBizConst.CODE_INVENTORY_CHANGE_TYPE_RESET);
        inventoryChangeTypes = getDropDownList(inventoryChangeTypeList);
        
        List<Integer> paymentTypeList = new ArrayList<Integer>();
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CASH);
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CHECK);
        paymentTypeList.add(MicroBizConst.CODE_PAYMENT_TYPE_CREDIT_CARD);
        paymentTypes = getDropDownList(paymentTypeList);
        
        List<Integer> quoteStatusList = new ArrayList<Integer>();
        quoteStatusList.add(MicroBizConst.CODE_STATUS_OPEN);
        quoteStatusList.add(MicroBizConst.CODE_STATUS_WON);
        quoteStatusList.add(MicroBizConst.CODE_STATUS_CANCELED);
        quoteStatusList.add(MicroBizConst.CODE_STATUS_FAILED);
        quoteStatus = getDropDownList(quoteStatusList);
        
        List<Integer> jobStatusList = new ArrayList<Integer>();
        jobStatusList.add(MicroBizConst.CODE_STATUS_OPEN);
        jobStatusList.add(MicroBizConst.CODE_STATUS_COMPLETED);
        jobStatusList.add(MicroBizConst.CODE_STATUS_CANCELED);
        jobStatus = getDropDownList(jobStatusList);
        
        List<Integer> invoiceStatusList = new ArrayList<Integer>();
        invoiceStatusList.add(MicroBizConst.CODE_STATUS_OPEN);
        invoiceStatusList.add(MicroBizConst.CODE_STATUS_CLOSED);
        invoiceStatusList.add(MicroBizConst.CODE_STATUS_CANCELED);
        invoiceStatus = getDropDownList(invoiceStatusList);
        
        List<Integer> paymentTermsList = new ArrayList<Integer>();
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
  
    private List<LabelValue> getDropDownList(List<Integer> keyList) {
        List<LabelValue> lvList = new ArrayList<LabelValue>();
        for ( Integer key : keyList ) {
            String label = PropertyHelper.getInstance().getLable(key);
            LabelValue lv = new LabelValue(label, key);
            lvList.add(lv);
        }
        return lvList;
    }
}
