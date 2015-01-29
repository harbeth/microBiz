package com.microBiz.controller.mgmt;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.MiRoleAccessRightMeta;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.MiRoleAccessRight;
import com.microBiz.model.MiUser;
import com.microBiz.model.Product;
import com.microBiz.service.MiRoleAccessRightService;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;



public class MiRoleAccessRightController extends BaseController{

    private MiRoleAccessRightService s;
    private MiRoleAccessRightMeta metaP;
    public MiRoleAccessRightController(){
        super();
        s = new MiRoleAccessRightService();
        metaP = new MiRoleAccessRightMeta();

    }
    @Override
    public Navigation run() throws Exception {

 
        if(asKey(metaP.key) != null){// from delete link
            s.delete(asKey(metaP.key));         
        }
        MiRoleAccessRight p = new MiRoleAccessRight();
       
            
       
        BeanUtil.copy(p, request);
        List<MiRoleAccessRight> roleRights = s.getAll();
        requestScope("roleRights", roleRights);
        requestScope("roles", roles);
        requestScope("modules", modules);
        return forward("roleAccessRight-wrapper.jsp");

       
    }
    
    
}
