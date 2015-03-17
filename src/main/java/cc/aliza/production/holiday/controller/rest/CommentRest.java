package cc.aliza.production.holiday.controller.rest;

import java.util.Date;

import cc.aliza.production.holiday.commons.Result;
import cc.aliza.production.holiday.dao.CommentDao;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.entity.Comment;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

/**
 * Created by Jing on 14-2-2.
 */
@Before(DataInterceptor.class)
public class CommentRest extends Controller {

    @Before(POST.class)
    public void save() {
        Comment comment = new Comment();
        comment.setContent(getPara("message"));
        
        Goods goods=GoodsDao.dao.findOne(getPara("goods"));
        
        comment.setGoods(goods);
        
        String goodsJson=new Gson().toJson(goods);
        
        comment.setGoodsJson(goodsJson);
        
        comment.setMember((Member) getAttr("member"));
        comment.setPhone(getPara("phone"));
        
        Date now = new Date(); 
        String commentdate=now.toLocaleString();
        
        comment.setCommentDate(commentdate);
        
        CommentDao.dao.save(comment);
        
        //renderJson(Result.exec(comment));
    }

}
