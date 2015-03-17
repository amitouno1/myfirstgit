package cc.aliza.production.holiday.controller.view;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import cc.aliza.production.holiday.dao.ADDao;
import cc.aliza.production.holiday.dao.ArgDao;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.LabelDao;
import cc.aliza.production.holiday.dao.PlayDao;
import cc.aliza.production.holiday.entity.AD;
import cc.aliza.production.holiday.entity.Arg;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Play;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.bugull.mongo.BuguMapper;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by Jing on 14-2-16.
 */
@Before(DataInterceptor.class)
public class HomeController extends Controller {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int recorderCounts=1000;

    public void index() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("status", 1);
        params.put("discount", 0);
        params.put("pageSize", recorderCounts);
        setAttr("discountGoodsPage", GoodsDao.dao.findBy(params));

        params.clear();
        params.put("pageSize", recorderCounts);
        params.put("production", "line");
        params.put("status", 1);
        Page<Goods> hotLineGoodsPage = GoodsDao.dao.findBy(params);
        BuguMapper.fetchCascade(hotLineGoodsPage.getList(), "labels");
        setAttr("hotLineGoodsPage", hotLineGoodsPage);
        
        params.clear();
        params.put("pageSize", recorderCounts);
        Page<Arg> argsPage = ArgDao.dao.findBy(params);
        setAttr("argsPage", argsPage);

        params.clear();
        params.put("production", "line");
        params.put("pageSize", recorderCounts);
        setAttr("hotLineLabelPage", LabelDao.dao.findBy(params));

        params.clear();
        params.put("production", "hotel");
        params.put("pageSize", recorderCounts);
        setAttr("hotHotelLabelPage", LabelDao.dao.findBy(params));

        params.clear();
        params.put("production", "ticket");
        params.put("pageSize", recorderCounts);
        setAttr("hotTicketLabelPage", LabelDao.dao.findBy(params));

        params.clear();
        params.put("production", "car");
        params.put("pageSize", recorderCounts);
        setAttr("hotCarLabelPage", LabelDao.dao.findBy(params));

        params.clear();
        params.put("pageSize", 15);
        params.put("production", "hotel");
        params.put("pageSize", recorderCounts);
        params.put("status", 1);
        setAttr("hotHotelGoodsPage", GoodsDao.dao.findBy(params));

        params.clear();
        params.put("pageSize", recorderCounts);
        params.put("production", "ticket");
        params.put("status", 1);
        setAttr("hotTicketGoodsPage", GoodsDao.dao.findBy(params));

        params.clear();
        params.put("pageSize", recorderCounts);
        params.put("production", "car");
        params.put("status", 1);
        setAttr("hotCarGoodsPage", GoodsDao.dao.findBy(params));

        params.clear();
        params.put("position", "LB");
        params.put("pageSize", recorderCounts);
        setAttr("LBADPage", ADDao.dao.findBy(params));
        
        params.clear();
        params.put("position", "jpjd");
        params.put("pageSize", recorderCounts);
        Page<AD> jpjdPage = ADDao.dao.findBy(params);

        setAttr("jpjdPage",jpjdPage);

        try {
            params.clear();
            params.put("pageSize", recorderCounts);
            params.put("type", "eat");

            Page<Play> eatPage = PlayDao.dao.findBy(params);

            setAttr("eatPage", eatPage);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
        
        try {
            params.clear();
            params.put("pageSize", recorderCounts);
            params.put("type", "go");

            Page<Play> goPage = PlayDao.dao.findBy(params);

            setAttr("goPage", goPage);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
        try {
            params.clear();
            params.put("pageSize", recorderCounts);
            params.put("type", "like");

            Page<Play> likePage = PlayDao.dao.findBy(params);

            setAttr("likePage", likePage);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
        try {
            params.clear();
            params.put("pageSize", recorderCounts);
            params.put("type", "buy");

            Page<Play> buyPage = PlayDao.dao.findBy(params);

            setAttr("buyPage", buyPage);
            
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
        
       
        setAttr("HF1AD", ADDao.dao.findOne("position", "HF1"));
        setAttr("HF2AD", ADDao.dao.findOne("position", "HF2"));
        setAttr("HF3AD", ADDao.dao.findOne("position", "HF3"));

        setAttr("JD1AD", ADDao.dao.findOne("position", "JD1"));
        setAttr("JD2AD", ADDao.dao.findOne("position", "JD2"));
        setAttr("JD3AD", ADDao.dao.findOne("position", "JD3"));
        setAttr("JD4AD", ADDao.dao.findOne("position", "JD4"));
        
        

        render("/view/index.html");
    }
}
