package com.microBiz;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.slim3.memcache.Memcache;
import org.slim3.util.CoolBridge;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.microBiz.service.MiRoleAccessRightService;
import com.microBiz.service.MiUserService;


public class PropertyHelper {
    
    private static PropertyHelper helper = null;
    MemcacheService syncCache;
    
    private PropertyHelper(){
      
        syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        
        Properties prop = (Properties) syncCache.get("prop");
        //Properties prop = (Properties) Memcache.get("prop");
        if (prop == null) {
            prop = new Properties();
            try {
                // microBiz.properties
                InputStream in = getClass().getResourceAsStream("/microBiz.properties");
                prop.load(in);
                syncCache.put("prop",prop);
                //Memcache.put("prop",prop);
            }catch(Exception e) {
                System.out.println("cannot find file: " + e.getMessage());
            }
        }
        Properties roleAccessible = (Properties) syncCache.get("roleAccessible");
        //Properties roleAccessible = (Properties) Memcache.get("roleAccessible");
        
        if (roleAccessible == null) {
            roleAccessible = new Properties();
            MiRoleAccessRightService miRoleService = new MiRoleAccessRightService();
            roleAccessible.put("admin", miRoleService.getAccessibleModuleByRole("admin"));
            roleAccessible.put("sales", miRoleService.getAccessibleModuleByRole("sales"));
            roleAccessible.put("installer", miRoleService.getAccessibleModuleByRole("installer"));
            roleAccessible.put("manager", miRoleService.getAccessibleModuleByRole("manager")); 
            syncCache.put("roleAccessible",roleAccessible);
            //Memcache.put("roleAccessible",roleAccessible);
        }
        
        MiUserService userService = new MiUserService();
        
        List installers = (List)syncCache.get("installers");
        //List installers = (List)Memcache.get("installers");
        if(installers == null){
           
            syncCache.put("installers",userService.getInstallerNames());
            //Memcache.put("installers",userService.getInstallerNames());
        }
        
        List sales = (List)syncCache.get("sales");
        //List sales = (List)Memcache.get("sales");
        if(installers == null){
            syncCache.put("sales",userService.getSalesNames());
            //Memcache.put("sales",userService.getSalesNames());
        }
        
    }
    
    public static PropertyHelper getInstance() {
        if ( helper == null ) {
            helper = new PropertyHelper();
        }
        return helper;
     }
    
    public String getLable(Integer constant){
        if(constant!=null){
            Properties prop = (Properties)syncCache.get("prop");
            //Properties prop = (Properties)Memcache.get("prop");
            return (String)prop.get(constant.toString()); 
        }else{
            return "";
        }
     
    }
    
    public String getRoleAccessible(String myRole){
        if(myRole!=null){
            Properties roleAccessible = (Properties)syncCache.get("roleAccessible");
            //Properties roleAccessible = (Properties)Memcache.get("roleAccessible");
            return (String)roleAccessible.get(myRole); 
        }else{
            return "";
        }
     
    }
    
    public List getInstallers(){
        return (List)syncCache.get("installers");
        //return (List)Memcache.get("installers");
 
    }
    
    public void setInstallers(List installers){
        syncCache.put("installers", installers);
       //Memcache.put("installers", installers);
    }
    
    public void setSales(List sales){
        syncCache.put("sales", sales);
        //Memcache.put("sales", sales);
    }
    
    public List getSales(){
        return (List)syncCache.get("sales");
        //return (List)Memcache.get("sales");
 
    }
    

}

