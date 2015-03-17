package cc.aliza.production.holiday.entity;

import com.bugull.mongo.SimpleEntity;
import com.bugull.mongo.annotations.Entity;
import com.bugull.mongo.annotations.Ref;
import com.google.gson.Gson;

/**
 * Created by kyosp17 on 14-12-1.
 */
@Entity
public class PJ extends SimpleEntity {
	
	
    // 关联商品
    @Ref
    private Goods goods;
    
    // 商品快照
    private String goodsJson;

    //订单编号
	private String orderid;
	
	//关联用户
    @Ref
	private Member member;
    
    // 用户快照
    private String memberJson;
	
	

	//评价日期
    private String pjDate;

    //评价备注
    private String content;
	
	//服务器质量
	private String fwzl;
	
	//行程安排
	private String xcap;
	
	//食宿品质
	private String sspz;
	
	//总体评价
	private String ztpj;
	
	public Goods getGoodsObject() {
        return new Gson().fromJson(this.goodsJson, Goods.class);
    }
	
	public Member getMemberObject() {
		
        return new Gson().fromJson(this.memberJson, Member.class);
    }
	
	

	public String getFwzl() {
		return fwzl;
	}

	public void setFwzl(String fwzl) {
		this.fwzl = fwzl;
	}

	public String getXcap() {
		return xcap;
	}

	public void setXcap(String xcap) {
		this.xcap = xcap;
	}

	public String getSspz() {
		return sspz;
	}

	public void setSspz(String sspz) {
		this.sspz = sspz;
	}

	public String getZtpj() {
		return ztpj;
	}

	public void setZtpj(String ztpj) {
		this.ztpj = ztpj;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
    public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	
	public String getPjDate() {
		return pjDate;
	}

	public void setPjDate(String pjDate) {
		this.pjDate = pjDate;
	}
	
	public String getGoodsJson() {
		return goodsJson;
	}


	public void setGoodsJson(String goodsJson) {
		this.goodsJson = goodsJson;
	}


	public String getMemberJson() {
		return memberJson;
	}


	public void setMemberJson(String memberJson) {
		this.memberJson = memberJson;
	}
    
}
