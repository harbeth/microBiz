package com.microBiz;

public interface MicroBizConst {
    
    String CUSTOMER_TYPE_RESIDENTIAL = "residential";
    String CUSTOMER_TYPE_COMMERCIAL = "commercial";
    
    public int ENTRIES_PER_PAGE = 10;
    
    //constants for microBiz.properties
    public Integer CODE_ROLE_ADMIN = new Integer(1) ;
    public Integer CODE_ROLE_INSTALLER = new Integer(2);
    public Integer CODE_ROLE_SALES = new Integer(3);
    public Integer CODE_ROLE_MANAGER =new Integer(4);
    
    public Integer CODE_UNIT_KG = new Integer(30);
    public Integer CODE_UNIT_POUND = new Integer(31);
    public Integer CODE_UNIT_STROKE = new Integer(32);
    public Integer CODE_UNIT_SQFT = new Integer(33);
    
    public Integer CODE_CUSTOMER_RATING_NORMAL =new Integer(40);
    public Integer CODE_CUSTOMER_RATING_VIP = new Integer(41);
    public Integer CODE_CUSTOMER_RATING_BAD = new Integer(42);
    
    
    public Integer CODE_PRODUCT_TYPE_SELLING = new Integer(50);
    public Integer CODE_PRODUCT_TYPE_RAW_MATERIAL = new Integer(51);
    public Integer CODE_PRODUCT_TYPE_BOTH = new Integer(52);

    
    public Integer CODE_CUSTOMER_TYPE_RESIDENTIAL =new Integer(70);
    public Integer CODE_CUSTOMER_TYPE_COMMERCIAL = new Integer(71);
    
    public Integer CODE_INVENTORY_CHANGE_TYPE_DESC = new Integer(80);
    public Integer CODE_INVENTORY_CHANGE_TYPE_INCR = new Integer(81);
    public Integer CODE_INVENTORY_CHANGE_TYPE_RESET = new Integer(82);
    
    public Integer CODE_PAYMENT_TYPE_CHECK = new Integer(90);
    public Integer CODE_PAYMENT_TYPE_CASH = new Integer(91);
    public Integer CODE_PAYMENT_TYPE_CREDIT_CARD = new Integer(92);
    
    public Integer CODE_STATUS_OPEN = new Integer(100);
    public Integer CODE_STATUS_WON = new Integer(101);
    public Integer CODE_STATUS_FAILED = new Integer(102);
    public Integer CODE_STATUS_COMPLETED = new Integer(103);
    public Integer CODE_STATUS_CANCELED = new Integer(104);
    public Integer CODE_STATUS_VOID = new Integer(105);
    public Integer CODE_STATUS_APPROVED = new Integer(106);
    public Integer CODE_STATUS_NEW = new Integer(107);
    public Integer CODE_STATUS_CLOSED = new Integer(108);
    
    
    public Integer CODE_PAYMENT_TERMS_0D = new Integer(110);
    public Integer CODE_PAYMENT_TERMS_30D = new Integer(111);
    public Integer CODE_PAYMENT_TERMS_45D = new Integer(112);
    
}
