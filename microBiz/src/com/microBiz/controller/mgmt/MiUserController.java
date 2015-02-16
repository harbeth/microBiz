package com.microBiz.controller.mgmt;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.controller.BaseController;
import com.microBiz.meta.MiUserMeta;
import com.microBiz.meta.ProductMeta;
import com.microBiz.model.MiUser;
import com.microBiz.model.Product;
import com.microBiz.service.MiUserService;
import com.microBiz.service.ProductService;



public class MiUserController extends BaseController{

    private MiUserService s;
    private MiUserMeta metaP;
    public MiUserController(){
        super();
        s = new MiUserService();
        metaP = new MiUserMeta();

    }
    @Override
    public Navigation run() throws Exception {

    
        MiUser p = null;
        if(asKey(metaP.key) != null){// from edit link
            p = s.get(asKey(metaP.key));         
        }else{
            p = new MiUser();
            p.setActive("on");
        }
        BeanUtil.copy(p, request);
        List<MiUser> uList = s.getAll();
        requestScope("users", uList);
        requestScope("roles", roles);
        return forward("user-wrapper.jsp");

       
    }
    
    
}
