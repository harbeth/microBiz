package com.microBiz.controller;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.microBiz.meta.PrdRatioMeta;
import com.microBiz.model.PrdRatio;
import com.microBiz.service.PrdRatioService;



public class PrdRatioController extends BaseController{

    private PrdRatioService s;
    private PrdRatioMeta metaP;
    public PrdRatioController(){
        super();
        s = new PrdRatioService();
        metaP = new PrdRatioMeta();

    }
    @Override
    public Navigation run() throws Exception {

    
        PrdRatio pcpr = null;
        if(asKey(metaP.key) != null){// from edit link
            pcpr = s.get(asKey(metaP.key));         
        }else{
            pcpr = new PrdRatio();
        }
        BeanUtil.copy(pcpr, request);
        List<PrdRatio> pcprList = s.getAll();
        requestScope("prdRs", pcprList);
        return forward("prdRatio.jsp");

       
    }
    
    
}
