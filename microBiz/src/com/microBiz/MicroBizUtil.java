package com.microBiz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MicroBizUtil {
    
    private static Calendar calendar = Calendar.getInstance();
    private MicroBizUtil() {
    }
    
    // for price, not save total
    public static double roundTo2Demcial(double value) {
        return Math.ceil(value * 100)/100;
    }
    
    public static String getQuoteVersionName(int count) {
        return "V" + count;
    }
    
    private static String getFixedString(int value) {
        String valueStr = String.valueOf(value);
        if ( value < 10 ) {
            valueStr = "0" + valueStr;
        }
        return valueStr;
    }
    
    // return MB-yyyy-mm-dd-hhmmss-mmm
    public static String generateInvoiceNumber(){
        //UUID.randomUUID().toString()
        calendar.setTimeInMillis(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        sb.append(calendar.get(Calendar.YEAR))
          .append(getFixedString(month)).append(getFixedString(dayOfMonth))
          .append("-").append(getFixedString(hourOfDay))
          .append(getFixedString(minute)).append(getFixedString(second))
          .append("-").append(calendar.get(Calendar.MILLISECOND));
        return sb.toString();

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
