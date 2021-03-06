package com.microBiz;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.microBiz.service.MiRoleAccessRightService;
import com.microBiz.service.MiUserService;


public class PropertyHelper {
    
    private static PropertyHelper helper = null;
     
    private PropertyHelper(){
      
  
        
        Properties prop = (Properties) getMemcache().get("prop");
        //Properties prop = (Properties) Memcache.get("prop");
        if (prop == null) {
            prop = loadConstantsPro();
            getMemcache().put("prop",prop);
        }
        Properties roleAccessible = (Properties) getMemcache().get("roleAccessible");
        //Properties roleAccessible = (Properties) Memcache.get("roleAccessible");
        
        if (roleAccessible == null) {
            roleAccessible = loadRoleAccessible();
            getMemcache().put("roleAccessible",roleAccessible);
            //Memcache.put("roleAccessible",roleAccessible);
        }
        
        MiUserService userService = new MiUserService();
        
        List<String> installers = (List<String>)getMemcache().get("installers");
        //List installers = (List)Memcache.get("installers");
        if(installers == null){
           
            getMemcache().put("installers",userService.getInstallerNames());
            //Memcache.put("installers",userService.getInstallerNames());
        }
        
        List<String> sales = (List<String>)getMemcache().get("sales");
        //List sales = (List)Memcache.get("sales");
        if(sales == null){
            getMemcache().put("sales",userService.getSalesNames());
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
            Properties prop = (Properties)getMemcache().get("prop");
            if (prop == null) {
                prop = loadConstantsPro();
                getMemcache().put("prop",prop);
            }
            //Properties prop = (Properties)Memcache.get("prop");
            return (String)prop.get(constant.toString()); 
        }else{
            return "";
        }
     
    }
    
    public String getRoleAccessible(String myRole){
        if(myRole!=null){
            Properties roleAccessible = (Properties)getMemcache().get("roleAccessible");
            //Properties roleAccessible = (Properties)Memcache.get("roleAccessible");
            if(roleAccessible==null){
                roleAccessible = loadRoleAccessible();
                getMemcache().put("roleAccessible",roleAccessible);
            }
            return (String)roleAccessible.get(myRole); 
        }else{
            return "";
        }
     
    }
    
    public List<String> getInstallers(){
        List<String> installers = (List<String>)getMemcache().get("installers");
        
        if(installers == null){
            MiUserService userService = new MiUserService();
            installers = userService.getInstallerNames();
            getMemcache().put("installers",installers);
            //Memcache.put("installers",userService.getInstallerNames());
        }
        return installers;
        
    }
    
    public void setInstallers(List<String> installers){
        getMemcache().put("installers", installers);
       //Memcache.put("installers", installers);
    }
    
    public void setSales(List<String> sales){
        getMemcache().put("sales", sales);
        //Memcache.put("sales", sales);
    }
    
    public List<String> getSales(){
        List<String> sales = (List<String>)getMemcache().get("sales");
        //List sales = (List)Memcache.get("sales");
        if(sales == null){
            MiUserService userService = new MiUserService();
            sales = userService.getSalesNames();
            getMemcache().put("sales",sales);
            //Memcache.put("sales",userService.getSalesNames());
        }
        return sales;
        //return (List)Memcache.get("sales");
    }
    
    private Properties loadRoleAccessible(){
       
        Properties roleAccessible = new Properties();
        MiRoleAccessRightService miRoleService = new MiRoleAccessRightService();
        roleAccessible.put("admin", miRoleService.getAccessibleModuleByRole("admin"));
        roleAccessible.put("sales", miRoleService.getAccessibleModuleByRole("sales"));
        roleAccessible.put("installer", miRoleService.getAccessibleModuleByRole("installer"));
        roleAccessible.put("manager", miRoleService.getAccessibleModuleByRole("manager")); 
        return roleAccessible;
            
    }
    
    private Properties loadConstantsPro(){
        
        Properties prop = new Properties();
        prop = new Properties();
        try {
            // microBiz.properties
            InputStream in = getClass().getResourceAsStream("/microBiz.properties");
            prop.load(in);
           
        }catch(Exception e) {
            System.out.println("cannot find file: " + e.getMessage());
        }
        return prop;
            
    }
    
    private MemcacheService getMemcache(){
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        return syncCache;
    }
    
    
}

