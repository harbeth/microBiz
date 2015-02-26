package com.microBiz;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class PropertyHelper {
    
    
    public String getConstantLable(String constant){
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        String lable = (String)syncCache.get(constant); // read from cache
        if (lable == null) {
            Properties prop = new Properties();
            try {
                // microBiz.properties
                InputStream in = getClass().getResourceAsStream("/microBiz.properties");
                prop.load(in);
                syncCache.putAll(prop);
            }catch(Exception e) {
                System.out.println("cannot find file: " + e.getMessage());
            }

          lable = (String)syncCache.get(constant); 
        }
        
        return lable;
    }

}
