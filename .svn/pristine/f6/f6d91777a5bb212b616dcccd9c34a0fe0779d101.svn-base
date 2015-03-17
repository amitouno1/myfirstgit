package cc.aliza.production.holiday.controller.view;

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
import cc.aliza.production.holiday.dao.MemberDao;
import cc.aliza.production.holiday.dao.pjDao;
import cc.aliza.production.holiday.entity.Arg;
import cc.aliza.production.holiday.entity.Comment;
import cc.aliza.production.holiday.entity.Goods;
import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.entity.PJ;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.bugull.mongo.BuguMapper;
import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by Jing on 14-2-16.
 */
@Before(DataInterceptor.class)
public class DetailController extends Controller {
    public void index() {
    	
        String id = getPara(0);
        try {
        	 
            Goods goods = GoodsDao.dao.findOne(id);

            BuguMapper.fetchCascade(goods, "args.father", "supplier", "services");

            setAttr("goods", goods);

            Map<String, Object> params = new HashMap<String, Object>();
            
            params.clear();
            params.put("pageSize", 10000);
            Page<Arg> argsPage = ArgDao.dao.findBy(params);
            setAttr("argsPage", argsPage);

            //---------------------------------------咨询建议开始
            params.clear();
            int pagenumber=getParaToInt("pageNumber",1);
            params.put("pageNumber", pagenumber);
            params.put("pageSize", 3);
            params.put("id", id);
            params.put("goods", goods);
            setAttr("commentPage", CommentDao.dao.findBy(params));
            //---------------------------------------咨询建议结束
            
            
            
            
            //---------------------------------------评价系统开始(基于单件商品查询)
            params.clear();
//            int _pagenumber=getParaToInt("_pageNumber",1);
//            params.put("pageNumber", _pagenumber);
            params.put("pageSize", 99999);
            params.put("goods", goods);
            Page<PJ> pj=pjDao.dao.findBy(params);
            
            //查询评价用户信息
            for(int kk =0;kk<pj.getList().size();kk++)
            {
            	String memberid=pj.getList().get(kk).getMember().getId();
            	
            	Member m=MemberDao.dao.findOne(memberid);
            	
//            	if(pj.getList()!=null)
//            	{
//            		pj.getList().get(kk).setMember(m);
//            	}
            	pj.getList().get(kk).setMember(m);
            }
            
            setAttr("pjPage", pj);
            
            int t_total=pj.getList().size();
            
            int level1=0;
            int level2=0;
            int level3=0;
            int level4=0;
            int level5=0;
            
            for(int i=0;i<pj.getList().size();i++)
            {
            	String temp=pj.getList().get(i).getZtpj();
            	
            	Integer ztpj=new Integer(temp);
            	
            	if(ztpj>=new Integer("13")&&ztpj<=new Integer("15"))
            	{
            		level5=level5+1;
            	}
            	if(ztpj>=new Integer("10")&&ztpj<=new Integer("12"))
            	{
            		level4=level4+1;
            	}
            	if(ztpj>=new Integer("7")&&ztpj<=new Integer("9"))
            	{
            		level3=level3+1;
            	}
            	if(ztpj>=new Integer("4")&&ztpj<=new Integer("6"))
            	{
            		level2=level2+1;
            	}
            	if(ztpj>=new Integer("1")&&ztpj<=new Integer("3"))
            	{
            		level1=level1+1;
            	}
            }
            
            setAttr("t_total",t_total);
            
            //整体满意度,总体评价，级别评价
            setAttr("level5",level5);
            setAttr("level5_per",Math.round((Double.parseDouble(level5+"")/Double.parseDouble(t_total+""))*100)+"%");
            setAttr("level4",level4);
            setAttr("level4_per",Math.round((Double.parseDouble(level4+"")/Double.parseDouble(t_total+""))*100)+"%");
            setAttr("level3",level3);
            setAttr("level3_per",Math.round((Double.parseDouble(level3+"")/Double.parseDouble(t_total+""))*100)+"%");
            setAttr("level2",level2);
            setAttr("level2_per",Math.round((Double.parseDouble(level2+"")/Double.parseDouble(t_total+""))*100)+"%");
            setAttr("level1",level1);
            setAttr("level1_per",Math.round((Double.parseDouble(level1+"")/Double.parseDouble(t_total+""))*100)+"%");
            
            setAttr("my_level_desc",Math.round((Double.parseDouble(level5+level4+"")/Double.parseDouble(t_total+""))*100)+"%");
            
            
            //---------------------------------------评价系统结束
            

            params.clear();
            params.put("production", goods.getProduction());
            params.put("hot", 1);
            params.put("status", 1);
            setAttr("hotGoodsPage", GoodsDao.dao.findBy(params));


            if (goods.getProduction().equals("line")) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar e = null;
                List<String> dates = new ArrayList<String>();
                try {
                    Date b = sdf.parse(goods.getStartTime());
                    if (calendar.before(b)) {
                        calendar.setTime(b);
                    }
                } catch (Exception ex) {
                    calendar.clone();
                }

                try {
                    e = (Calendar) calendar.clone();
                    e.setTime(sdf.parse(goods.getEndTime()));
                } catch (Exception ex) {
                    e = (Calendar) calendar.clone();
                }
                e.add(Calendar.DAY_OF_MONTH, 1);
                while (calendar.before(e)) {
                    dates.add(sdf.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                setAttr("dates", dates);
                render("/view/detail_line.html");

            }else if(goods.getProduction().equals("hotel"))
            {
            	Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar e = null;
                List<String> dates = new ArrayList<String>();
                List<String> dates1 = new ArrayList<String>();
                try {
                    Date b = sdf.parse(goods.getStartTime());
                    if (calendar.before(b)) {
                        calendar.setTime(b);
                    }
                } catch (Exception ex) {
                    calendar.clone();
                }

                try {
                    e = (Calendar) calendar.clone();
                    e.setTime(sdf.parse(goods.getEndTime()));
                } catch (Exception ex) {
                    e = (Calendar) calendar.clone();
                }
                e.add(Calendar.DAY_OF_MONTH, 1);
                while (calendar.before(e)) {
                    dates.add(sdf.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1); 
                    dates1.add(sdf.format(calendar.getTime()));
                }
                
//                setAttr("dates", dates);
//            	render("/view/detail.html");
            	
                setAttr("dateBegin", dates);
                setAttr("dateEnd", dates1);
            	render("/view/detail_hotel.html");
            }else if(goods.getProduction().equals("ticket"))
            {
            	Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar e = null;
                List<String> dates = new ArrayList<String>();
                try {
                    Date b = sdf.parse(goods.getStartTime());
                    if (calendar.before(b)) {
                        calendar.setTime(b);
                    }
                } catch (Exception ex) {
                    calendar.clone();
                }

                try {
                    e = (Calendar) calendar.clone();
                    e.setTime(sdf.parse(goods.getEndTime()));
                } catch (Exception ex) {
                    e = (Calendar) calendar.clone();
                }
                e.add(Calendar.DAY_OF_MONTH, 1);
                while (calendar.before(e)) {
                    dates.add(sdf.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                setAttr("dates", dates);

            	render("/view/detail_ticket.html");
            }
        } catch (Exception e) {
            redirect("/");
        }
    }
    
    @Before(POST.class)
    public void getComment() {

    	Map<String, Object> params = new HashMap<String, Object>();
         
        String id = getPara("id");
       
        int pageNumber=getParaToInt("pageNumber");
       
        
        int pageSize=getParaToInt("pageSize");
        
        params.put("goods.id", id);
        params.put("pageNumber", pageNumber);
        params.put("pageSize", pageSize);
        
        Page< Comment>  commentList=CommentDao.dao.findBy(params);
        

        Gson g=new Gson();
        this.renderJson(g.toJson(commentList));
       
//        renderJson(Result.exec());

    }
    
   
    
    
}
