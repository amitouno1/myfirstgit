package cc.aliza.production.holiday.controller.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.aliza.production.holiday.dao.ArgDao;
import cc.aliza.production.holiday.dao.CommentDao;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.LabelDao;
import cc.aliza.production.holiday.dao.MemberDao;
import cc.aliza.production.holiday.dao.pjDao;
import cc.aliza.production.holiday.entity.Arg;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.entity.PJ;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.bugull.mongo.BuguMapper;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;



@Before(DataInterceptor.class)
public class mainController  extends Controller {
	
	int recorderCounts=1000;
	
	public void index() {
		
	    Map<String, Object> params = new HashMap<String, Object>();


	    params.clear();
        params.put("pageSize", recorderCounts);
        params.put("production", "line");
        params.put("status", 1);
        Page<Goods> hotLineGoodsPage = GoodsDao.dao.findBy(params);
        BuguMapper.fetchCascade(hotLineGoodsPage.getList(), "labels");
        setAttr("hotLineGoodsPage", hotLineGoodsPage);
	        
		
		
		render("/mobile/main.html");
    }
}
