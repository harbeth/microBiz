package com.microBiz;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.slim3.memcache.Memcache;


public class PropertyHelper {
    private static PropertyHelper helper = new PropertyHelper( );
    
    private PropertyHelper(){
  
        String lable = (String)Memcache.get(MicroBizConst.CODE_CUSTOMER_RATING_BAD);
        if (lable == null) {
            Properties prop = new Properties();
            try {
                // microBiz.properties
                InputStream in = getClass().getResourceAsStream("/microBiz.properties");
                prop.load(in);
                Memcache.putAll(prop);
            }catch(Exception e) {
                System.out.println("cannot find file: " + e.getMessage());
            }
        }
        
    }
    
    public static PropertyHelper getInstance( ) {
        return helper;
     }
    public static String getLable(Integer constant){

         return (String)Memcache.get(constant.toString()); 
     
    }

}

