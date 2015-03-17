package cc.aliza.production.holiday.controller.view;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cc.aliza.production.holiday.dao.ADDao;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.LabelDao;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by Jing on 14-2-8.
 */
@Before({DataInterceptor.class})
public class LineController extends Controller {

    public void index() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("production", "line");
        setAttr("labelPage", LabelDao.dao.findBy(params));

        params.put("label", getPara("label", null));
        params.put("target", getPara("target", null));
        params.put("order", getPara("order", null));
        params.put("desc", getPara("desc", null));
        params.put("key", getPara("key", null));
        
        params.put("code", getPara("code", null)) ;
        
        params.put("status", 1);
        
        params.put("pageSize", 9);
        
        setAttr("order", getPara("order", null));
        setAttr("desc", getPara("desc", null));
        setAttr("queryLabel", getPara("label", null));
        setAttr("queryTarget", getPara("target", null));
        setAttr("code", getPara("code", null));
       
        int pagenumber=getParaToInt("pageNumber",1);
        params.put("pageNumber", pagenumber);
        setAttr("pageNumber", pagenumber);
        
        Page<Goods> goods=null;
        
        if(getPara("code", null)==null)
        {
        	goods=GoodsDao.dao.findBy(params);
        }else
        {
        	goods=GoodsDao.dao.findByCode(params,getPara("code", null));
        }
        
        
//        goods.getTotalPage();
        
        setAttr("goodsPage",goods);

        params.clear();
        params.put("position", "LINELB");
        setAttr("LBADPage", ADDao.dao.findBy(params));

        setAttr("BLOCKAD", ADDao.dao.findOne("position", "LINEBLOCK"));
        
//        long totalcounts=GoodsDao.dao.query().is("status", 1).is("production", "line").count();
//
//        setAttr("count",totalcounts );

        render("/view/line/index.html");
    }


}
