package cc.aliza.production.holiday.controller.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.pjDao;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.entity.PJ;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.bugull.mongo.BuguMapper;
import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

/**
 * Created by Jing on 14-2-2.
 */
@Before(DataInterceptor.class)
public class PjRest extends Controller {

    @Before(POST.class)
    public void save() {
    	
    	PJ pj=new PJ();
    	
    	String orderid=getPara("orderid");
    	String message=getPara("message");
    	String goodsid=getPara("goodsid");
    	
    	String fwzl=getPara("fwzl");
    	String xcap=getPara("xcap");
    	String sspz=getPara("sspz");
    	String ztpj=getPara("ztpj");
    	
    	//获取当前登陆用户
    	Member member=getAttr("member");
    	
    	 //查询商品信息
        Goods goods = GoodsDao.dao.findOne(goodsid);
        BuguMapper.fetchCascade(goods, "args.father", "supplier", "services");
    	
    	//获取当前时间
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String pjdate=df.format(new Date());// new Date()为获取当前系统时间
    	
    	pj.setOrderid(orderid);
    	pj.setContent(message);
    	pj.setGoods(goods);
    	pj.setGoodsJson(new Gson().toJson(goods));
    	pj.setMember(member);
    	pj.setMemberJson(new Gson().toJson(member));
    	pj.setPjDate(pjdate);
    	
    	pj.setFwzl(fwzl);
    	pj.setXcap(xcap);
    	pj.setSspz(sspz);
    	pj.setZtpj(ztpj);
    	
	    pjDao.dao.save(pj);
        
        //renderJson(Result.exec(comment));
    }

}
