package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.PrdRatioMeta;
import com.microBiz.model.PrdRatio;
import com.microBiz.service.PrdRatioService;



public class PrdRatioActionController extends BaseController{

    private PrdRatioService s;
    private PrdRatioMeta metaP;
    public PrdRatioActionController(){
        super();
        s = new PrdRatioService();
        metaP = new PrdRatioMeta();

    }
    @Override
    public Navigation run() throws Exception {

        PrdRatio prdR = null;
        
        if(asKey(metaP.key)!= null){ // update
            prdR = s.get(asKey(metaP.key));
            BeanUtil.copy(request,prdR); 

        }else{ // insert new
            prdR = new PrdRatio();
            BeanUtil.copy(request,prdR); 
 
        }
        s.save(prdR);
        List<PrdRatio> pcprList = s.getAll();
        requestScope("prdRs", pcprList);
        return redirect("/prdRatio");

       
    }
    
    
}
