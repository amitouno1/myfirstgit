package cc.aliza.production.holiday.controller.view;

import java.util.HashMap;
import java.util.Map;

import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by Jing on 14-2-16.
 */
@Before(DataInterceptor.class)
public class TUANGOUController extends Controller {
    public void index() {
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	
    	 
        params.put("production", "line");
        params.put("status", 1);
        
        params.put("pageSize",99999);
        
        Page<Goods> goodsPages=GoodsDao.dao.findBy(params);

        setAttr("goodsPages", goodsPages);
        
        render("/view/tuangou/tuangou.html");
    }
}
