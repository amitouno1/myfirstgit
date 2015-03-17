package cc.aliza.production.holiday.dao;

import java.util.List;
import java.util.Map;

import cc.aliza.production.holiday.entity.Member;
import cc.aliza.production.holiday.entity.PJ;

import com.bugull.mongo.BuguDao;
import com.bugull.mongo.BuguMapper;
import com.bugull.mongo.BuguQuery;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created by Jing on 14-1-27.
 */
public class pjDao extends BuguDao<PJ> {

    public static pjDao dao = new pjDao();

    public pjDao() {
        super(PJ.class);
    }

    public Page<PJ> findBy(Map<String, Object> params) {

        BuguQuery<PJ> query = query();

        if (params.get("goods") != null) {
            query.is("goods", params.get("goods"));
        }
        
        if (params.get("username_search") != null) {
        	
        	  Member member=MemberDao.dao.findOne("username", params.get("username_search"));
        	  
              query.is("member", member);
        }
        
        Integer pageNumber = 1;
        if (params.get("pageNumber") != null) {
        	pageNumber=new Integer(params.get("pageNumber").toString());
        	
        }
        query.pageNumber(pageNumber);
        
        //按日期倒序排列
        query.sort("{pjDate: -1}");

        Integer pageSize = 10;
        if (params.get("pageSize") != null) {
            pageSize = (Integer) params.get("pageSize");
        }
        query.pageSize(pageSize);

        int totalRow = (int) query.count();
        int totalPage = (int) Math.ceil((double) totalRow / pageSize);
        List<PJ> PJList = query.results();

        return new Page<PJ>(PJList, pageNumber, pageSize, totalPage, totalRow);
    }
}
