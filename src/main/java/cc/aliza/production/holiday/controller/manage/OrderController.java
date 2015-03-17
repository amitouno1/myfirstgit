package cc.aliza.production.holiday.controller.manage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cc.aliza.production.holiday.commons.Result;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.MemberDao;
import cc.aliza.production.holiday.dao.OrderDao;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.entity.Order;
import cc.aliza.production.holiday.interceptor.manage.AuthInterceptor;

import com.bugull.mongo.BuguMapper;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

/**
 * Created by Jing on 14-2-11.
 */
@Before(AuthInterceptor.class)
public class OrderController extends Controller {

    public void index() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("pageNumber", getParaToInt("pageNumber"));
        params.put("status", getParaToInt("status"));

        String id = getPara("member");
        if (StringUtils.isNotBlank(id)) {
            Member member = MemberDao.dao.findOne(id);
            params.put("member", member);
            setAttr("member", member);
        }
        setAttr("status", getParaToInt("status"));
        setAttr("orderPage", OrderDao.dao.findBy(params));
        
        render("/manage/order/index.html");
    }

    public void info() {
        Order order = OrderDao.dao.findOne(getPara(0));
        BuguMapper.fetchCascade(order, "member");
        
        String goodsid=order.getGoods().getId();
        
        Goods goods =GoodsDao.dao.findOne(goodsid);
        setAttr("goodsPage", goods);
        
        setAttr("order", order);
        render("/manage/order/info.html"); 
    }
    
    
    @Before(POST.class)
    public void deleteorder() {
        String id = getPara("id");
        OrderDao.dao.remove(id);
        renderJson(Result.exec());
    }
    
    @Before(POST.class)
    public void saveedit() {
        String id = getPara("id");
        Order order = OrderDao.dao.findOne(id);

        Integer order_payPrice=new Integer(getPara("order_payPrice").toString());
        
        String remark = getPara("remark");
        
        
        if(order_payPrice<0)
        {
        	order.setRemark(remark);
        	OrderDao.dao.save(order);
        	
        }else if(order_payPrice>0)
        {
        
        	Order newOrder=new Order();
			newOrder.setAmount(order.getAmount());
			newOrder.setBeginDate(order.getBeginDate());
			newOrder.setContactName(order.getContactName());
			newOrder.setContactPhone(order.getContactPhone());
			newOrder.setDate(order.getDate());
			newOrder.setDiscount(order.getDiscount());
			newOrder.setEndDate(order.getEndDate());
			newOrder.setGoods(order.getGoods());
			newOrder.setGoodsJson(order.getGoodsJson());
			newOrder.setMember(order.getMember());
			newOrder.setMessage(order.getMessage());
			newOrder.setNumber(order.getNumber());
			newOrder.setNumber1(order.getNumber1());
			newOrder.setNumber2(order.getNumber2());
			newOrder.setPayMethod(order.getPayMethod());
			//           newOrder.setPayPrice(payPrice);
			newOrder.setPrice(order.getPrice());
			 newOrder.setPriceSet(order.getPriceSet());
			//           newOrder.setRemark(remark);
			newOrder.setStatus(order.getStatus());
			newOrder.setTrades(order.getTrades());
			newOrder.setTravelers(order.getTravelers());
			 
			newOrder.setPayPrice(order_payPrice*100);
			newOrder.setRemark(remark);
			 
			OrderDao.dao.save(newOrder);
			 
			OrderDao.dao.remove(order);
        }
        
        renderJson(Result.exec());

    }

}