package cc.aliza.production.holiday.controller.view;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.TargetDao;
import cc.aliza.production.holiday.entity.Target;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created by Jing on 14-2-8.
 */
@Before({DataInterceptor.class})
public class AllController extends Controller {

    public void index() {

        Map<String, Object> params = new HashMap<String, Object>();

//        
//        try {
//			searchKey=new String(getPara("target", null).getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
        
        String  searchKey="";
        
        searchKey=getPara("target", null);
		

		int pagenumber=getParaToInt("pageNumber",1);
        params.put("pageNumber", pagenumber);
        params.put("key", getPara("key", null));
        params.put("order", getPara("order", null));
        params.put("desc", getPara("desc", null));
        params.put("status", 1);
        params.put("pageSize",9999);

        
        setAttr("order", getPara("order", null));
        setAttr("desc", getPara("desc", null));
        setAttr("key", getPara("key", null));
        setAttr("goodsPage", GoodsDao.dao.findBySearchKey(params,searchKey));
        
       
        render("/view/all/index.html");

        
        
    }

}
