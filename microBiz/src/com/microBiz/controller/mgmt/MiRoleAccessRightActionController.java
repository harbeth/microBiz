package com.microBiz.controller.mgmt;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.model.MiRoleAccessRight;
import com.microBiz.service.MiRoleAccessRightService;



public class MiRoleAccessRightActionController extends BaseController{
    private MiRoleAccessRightService s;
    //private MiRoleAccessRightMeta metaP;
    public MiRoleAccessRightActionController(){
        super();
        s = new MiRoleAccessRightService();
        //metaP = new MiRoleAccessRightMeta();
    }
    @Override
    public Navigation run() throws Exception {

        MiRoleAccessRight p =  new MiRoleAccessRight();
            BeanUtil.copy(request,p); 
        s.save(p);
        List<MiRoleAccessRight> roleRights = s.getAll();
        requestScope("roleRights", roleRights);
        requestScope("roles", roles);
        requestScope("modules", modules);
        return forward("roleAccessRight-wrapper.jsp");      
    }
    
    
}
