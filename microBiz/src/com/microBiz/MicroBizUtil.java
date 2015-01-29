package com.microBiz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.RandomStringUtils;

public class MicroBizUtil {
    private MicroBizUtil() {
    }
    
    // for price, not save total
    public static double roundTo2Demcial(double value) {
        return Math.ceil(value * 100)/100;
    }
    
    public static String getQuoteVersionName(int count) {
        return "V" + count;
    }
    
    public static String generateInvoiceNumber(){
        return RandomStringUtils.randomNumeric(8);

    }
    public static String parseDateToStr(Date d) {
        if(d!=null){
            SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy"); 
            dt.setTimeZone(TimeZone.getTimeZone("America/Toronto"));
            return dt.format(d);
        }else{
            return "";
        }

    }
    
    public  static Date parseStrToDate(String s) {
        if(s!=null){
            SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy"); 
            dt.setTimeZone(TimeZone.getTimeZone("America/Toronto"));
            Date result = null;
            try{
                result = dt.parse(s);
            }catch(Exception e){
                
            }
            return result;
        }else{
            return null;
        }

    }
}
