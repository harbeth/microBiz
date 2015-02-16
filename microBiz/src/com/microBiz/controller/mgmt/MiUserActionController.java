package com.microBiz.controller.mgmt;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.model.MiUser;
import com.microBiz.service.MiUserService;



public class MiUserActionController extends BaseController{
    private MiUserService s;
    private MiUserMeta metaP;
    public MiUserActionController(){
        super();
        s = new MiUserService();
        metaP = new MiUserMeta();

    }
    @Override
    public Navigation run() throws Exception {

        MiUser p = null;
        
        if(asKey(metaP.key)!= null){ // update
            p = s.get(asKey(metaP.key));
            BeanUtil.copy(request,p); 
            if(asString("active")==null){
                p.setActive("");
            }
        }else{ // insert new
            p = new MiUser();
            
            BeanUtil.copy(request,p); 
 
        }
        s.save(p);
        List<MiUser> users = s.getAll();
        requestScope("users", users);
        return forward("user-wrapper.jsp");

       
    }
    
    
}
