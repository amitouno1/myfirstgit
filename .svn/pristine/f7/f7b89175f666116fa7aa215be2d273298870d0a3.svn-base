package cc.aliza.production.holiday.controller.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.aliza.production.holiday.dao.CartDao;
import cc.aliza.production.holiday.dao.GoodsDao;
import cc.aliza.production.holiday.dao.OrderDao;
import cc.aliza.production.holiday.entity.Cart;
import cc.aliza.production.holiday.entity.Order;
import cc.aliza.production.holiday.interceptor.view.DataInterceptor;
import cc.aliza.production.holiday.interceptor.view.LoginInterceptor;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.bugull.mongo.BuguMapper;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

/**
 * Created by Jing on 14-2-8.
 */
@Before({LoginInterceptor.class, DataInterceptor.class})
public class PayController extends Controller {

    public void index() {

        String id = getPara(0);

        Order order = OrderDao.dao.findOne(id);

        if (order.getMember() == null) {
            OrderDao.dao.set(id, "member", getAttr("member"));
        } else {
            if (!order.getMember().equals(order.getMember())) {
                redirect("/");
                return;
            }
        }

        setAttr("order", order);
        render("/view/pay.html");
    }

    public void cart() {

        String id = getPara(0);

        Cart cart = CartDao.dao.findOne(id);
        BuguMapper.fetchCascade(cart, "orders");

        if (!cart.getMember().equals(cart.getMember())) {
            redirect("/");
            return;
        }

        setAttr("cart", cart);
        render("/view/payCart.html");
    }

    public void doPay() {
        Order order = OrderDao.dao.findOne(getPara(0));
        
        String payment_type = "1";
//        String notify_url = "http://localhost:8080/pay/pay_notify";
//        String return_url = "http://localhost:8080/pay/done/";
        
        String notify_url = "http://www.jiarifengguang.com/pay/pay_notify";
        String return_url = "http://www.jiarifengguang.com/pay/done/";
        String seller_email = "jiarifengguang@126.com";
        String out_trade_no = order.getId();
        String subject = order.getGoodsObject().getName();
        String price = String.valueOf(order.getPayPrice() / 100);
        String quantity = "1";
        String logistics_fee = "0.00";
        String logistics_type = "EXPRESS";
        String logistics_payment = "SELLER_PAY";
        String body = order.getGoodsObject().getCaption();
        String show_url = "http://www.jiarifengguang.com/detail/" + order.getGoods().getId();
//        String show_url = "http://localhost:8080/detail/" + order.getGoods().getId();
        String receive_name = "";
        String receive_address = "";
        String receive_zip = "";
        String receive_phone = "";
        String receive_mobile = "";

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_partner_trade_by_buyer");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("price", price);
        sParaTemp.put("quantity", quantity);
        sParaTemp.put("logistics_fee", logistics_fee);
        sParaTemp.put("logistics_type", logistics_type);
        sParaTemp.put("logistics_payment", logistics_payment);
        sParaTemp.put("body", body);
        
        
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("receive_name", receive_name);
        sParaTemp.put("receive_address", receive_address);
        sParaTemp.put("receive_zip", receive_zip);
        sParaTemp.put("receive_phone", receive_phone);
        sParaTemp.put("receive_mobile", receive_mobile);
        
        
        String sHtmlText= AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

        setAttr("sHtmlText",sHtmlText);
        
//        setAttr("sHtmlText","kyosp17");
//        this.pay_notify();
//       this.done();
        
        render("/view/doPay.html");

    }
    
    
    
    public void bankPay() {
        Order order = OrderDao.dao.findOne(getPara(0));
        
        String bankcode = getPara("bankcode");
  		
  		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		 String notify_url = "http://www.jiarifengguang.com/pay/pay_banknotify";
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		 String return_url = "http://www.jiarifengguang.com/pay/bankdone/";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		//卖家支付宝帐户
		String seller_email ="jiarifengguang@126.com";
		//必填

		//商户订单号
		String out_trade_no = order.getId();
		//商户网站订单系统中唯一订单号，必填

		//订单名称
		String subject = order.getGoodsObject().getName();
		//必填

		//付款金额
		String total_fee = String.valueOf(order.getPayPrice() / 100);
		//必填

		//订单描述

		String body = order.getGoodsObject().getCaption();
		
		//默认支付方式
		String paymethod = "bankPay";
		//必填
		//默认网银
		String defaultbank = bankcode;
		//必填，银行简码请参考接口技术文档

		//商品展示地址
		String show_url = "http://www.jiarifengguang.com/detail/" + order.getGoods().getId();
		//需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "211.149.185.7";
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("paymethod", paymethod);
		sParaTemp.put("defaultbank", defaultbank);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		
		setAttr("sHtmlText",sHtmlText);
        
        render("/view/doPay.html");

    }
    
    public void doPayCart() {
        Cart order = CartDao.dao.findOne(getPara(0));

        String payment_type = "1";
        String notify_url = "http://www.jiarifengguang.com/pay/payCart_notify";
        String return_url = "http://www.jiarifengguang.com/pay/cartDone/";
        String seller_email = "jiarifengguang@126.com";
        String out_trade_no = order.getId();
        String subject = "【假日风格】旅游产品";
        String price = String.valueOf(order.getPayPrice() / 100);
        String quantity = "1";
        String logistics_fee = "0.00";
        String logistics_type = "EXPRESS";
        String logistics_payment = "SELLER_PAY";
        String receive_name = "";
        String receive_address = "";
        String receive_zip = "";
        String receive_phone = "";
        String receive_mobile = "";

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_partner_trade_by_buyer");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("price", price);
        sParaTemp.put("quantity", quantity);
        sParaTemp.put("logistics_fee", logistics_fee);
        sParaTemp.put("logistics_type", logistics_type);
        sParaTemp.put("logistics_payment", logistics_payment);
        sParaTemp.put("body", "");
        sParaTemp.put("show_url", "");
        sParaTemp.put("receive_name", receive_name);
        sParaTemp.put("receive_address", receive_address);
        sParaTemp.put("receive_zip", receive_zip);
        sParaTemp.put("receive_phone", receive_phone);
        sParaTemp.put("receive_mobile", receive_mobile);

        setAttr("sHtmlText", AlipaySubmit.buildRequest(sParaTemp, "get", "确认"));
        
//        setAttr("sHtmlText","kyosp17");
//        this.payCart_notify();
//        this.cartDone();
        
        render("/view/doPay.html");
    }

    public void done() {
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            
            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            
            
//            //商户订单号
//            String out_trade_no = getPara(0);
//            //支付宝交易号
//            String trade_no = "我操你大爷";
//            //交易状态
//            String trade_status = "WAIT_SELLER_SEND_GOODS";


//            if (AlipayNotify.verify(params)) {//验证成功
////            if (true) {//验证成功
//                //////////////////////////////////////////////////////////////////////////////////////////
//                //请在这里加上商户的业务逻辑程序代码
//
//                Order order = OrderDao.dao.findOne(out_trade_no);
//
//
//                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//
//                if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//                    if (order.getStatus() == 1) {
//                        OrderDao.dao.set(order, "status", 2);
//                        GoodsDao.dao.inc(order.getGoods(), "sales", 1);
//
//                    }
//                }
//
//                //该页面可做页面美工编辑
//                setAttr("order", order);
//                render("/view/payDone.html");
//
//                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//                //////////////////////////////////////////////////////////////////////////////////////////
//            } else {
//                //该页面可做页面美工编辑
//                redirect("/user/order");
//            }
            
            
            
            Order order = OrderDao.dao.findOne(out_trade_no);
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                if (order.getStatus() == 1) {
                    OrderDao.dao.set(order, "status", 2);
                    GoodsDao.dao.inc(order.getGoods(), "sales", 1);

                }
            }

            //该页面可做页面美工编辑
            setAttr("order", order);
            render("/view/payDone.html");
            
            
            
        } catch (Exception e) {
            redirect("/user/order");
        }
    }
    
    public void bankdone() {
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            
            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            

            Order order = OrderDao.dao.findOne(out_trade_no);
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                if (order.getStatus() == 1) {
                    OrderDao.dao.set(order, "status", 2);
                    GoodsDao.dao.inc(order.getGoods(), "sales", 1);

                }
            }

            //该页面可做页面美工编辑
            setAttr("order", order);
            render("/view/payDone.html");
            
        } catch (Exception e) {
            redirect("/user/order");
        }
    }

    public void cartDone() {

        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            
            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号

            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            
            
//            //商户订单号
//            String out_trade_no = getPara(0);
//            //支付宝交易号
//            String trade_no = "我操你大爷";
//            //交易状态
//            String trade_status = "WAIT_SELLER_SEND_GOODS";

//            if (AlipayNotify.verify(params)) {//验证成功
////            if (true) {//验证成功
//                //////////////////////////////////////////////////////////////////////////////////////////
//                //请在这里加上商户的业务逻辑程序代码
//
//                Cart order = CartDao.dao.findOne(out_trade_no);
//
//                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//
//                if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//                    if (order.getStatus() == 1) {
//                        CartDao.dao.set(order, "status", 2);
//
//                        for (Order o : order.getOrders()) {
//                            OrderDao.dao.set(o, "status", 2);
//                        }
//                        
//                        
//                        
//	                   Map<String, String> trade = new HashMap<String, String>();
//	                   trade.put("trade_no", trade_no);
//	                   trade.put("trade_status", trade_status);
//	                   trade.put("time", String.valueOf(new Date().getTime()));
//	                  
//	                   List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
//	                   tradeList.add(trade);
//	                  
//	                   CartDao.dao.push(order, "trades", tradeList);
//
//                    }
//                }
//
//                //该页面可做页面美工编辑
//                setAttr("order", order);
//                render("/view/payDone.html");
//
//                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//                //////////////////////////////////////////////////////////////////////////////////////////
//            } else {
//                //该页面可做页面美工编辑
//                redirect("/user/order");
//            }
            

            Cart order = CartDao.dao.findOne(out_trade_no);

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                if (order.getStatus() == 1) {
                    CartDao.dao.set(order, "status", 2);

                    for (Order o : order.getOrders()) {
                        OrderDao.dao.set(o, "status", 2);
                    }
                    
                    
                   Map<String, String> trade = new HashMap<String, String>();
                   trade.put("trade_no", trade_no);
                   trade.put("trade_status", trade_status);
                   trade.put("time", String.valueOf(new Date().getTime()));
                  
                   List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
                   tradeList.add(trade);
                  
                   CartDao.dao.push(order, "trades", tradeList);

                }
            }

            //该页面可做页面美工编辑
            setAttr("order", order);
            render("/view/payDone.html");
            
            
        } catch (Exception e) {
            redirect("/user/order");
        }
    }
    

    @ClearInterceptor(ClearLayer.ALL)
    @Before(POST.class)
    public void pay_notify() {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();

            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            
            
//            //商户订单号
//            String out_trade_no = getPara(0);
//            //支付宝交易号
//            String trade_no = "我操你大爷";
//            //交易状态
//            String trade_status = "WAIT_SELLER_SEND_GOODS";

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

//            if (AlipayNotify.verify(params)) {//验证成功
////            if (true) {//验证成功
//                //////////////////////////////////////////////////////////////////////////////////////////
//                //请在这里加上商户的业务逻辑程序代码
//
//                Order order = OrderDao.dao.findOne(out_trade_no);
//
//                Map<String, String> trade = new HashMap<String, String>();
//                trade.put("trade_no", trade_no);
//                trade.put("trade_status", trade_status);
//                trade.put("time", String.valueOf(new Date().getTime()));
//
//                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//                if (trade_status.equals("WAIT_BUYER_PAY")) {
//                    //该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
//                    trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录，但没有付款");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
//                    //该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
//                    trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货");
////                    if (order.getStatus() == 1) {
////                        OrderDao.dao.set(order, "status", 2);
////                        GoodsDao.dao.inc(order.getGoods(), "sales", 1);
////                    }
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
//                    //该判断表示卖家已经发了货，但买家还没有做确认收货的操作
//                    trade.put("trade_status_message", "卖家已经发了货，但买家还没有做确认收货的操作");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("TRADE_FINISHED")) {
//                    //该判断表示买家已经确认收货，这笔交易完成
//                    trade.put("trade_status_message", "买家已经确认收货，这笔交易完成");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else {
//                    renderText("success");    //请不要修改或删除
//                }
//                
////                OrderDao.dao.push(order, "trades",trade);
//
//                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//                List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
//                tradeList.add(trade);
//                
//                OrderDao.dao.push(order, "trades",tradeList);
//        
//                //////////////////////////////////////////////////////////////////////////////////////////
//            } else {
//            	//验证失败
//                renderText("fail");
//            }
            
            Order order = OrderDao.dao.findOne(out_trade_no);

            Map<String, String> trade = new HashMap<String, String>();
            trade.put("trade_no", trade_no);
            trade.put("trade_status", trade_status);
            trade.put("time", String.valueOf(new Date().getTime()));

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("WAIT_BUYER_PAY")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
                trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录，但没有付款");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
                trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货");
                if (order.getStatus() == 1) {
                    OrderDao.dao.set(order, "status", 2);
                    GoodsDao.dao.inc(order.getGoods(), "sales", 1);
                }

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
                //该判断表示卖家已经发了货，但买家还没有做确认收货的操作
                trade.put("trade_status_message", "卖家已经发了货，但买家还没有做确认收货的操作");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("TRADE_FINISHED")) {
                //该判断表示买家已经确认收货，这笔交易完成
                trade.put("trade_status_message", "买家已经确认收货，这笔交易完成");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else {
                renderText("success");    //请不要修改或删除
            }
            
//            OrderDao.dao.push(order, "trades",trade);

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
            tradeList.add(trade);
            
            OrderDao.dao.push(order, "trades",tradeList);   
            
        } catch (Exception e) {
            renderText("fail");
        }
    }
    
    
    
    @ClearInterceptor(ClearLayer.ALL)
    @Before(POST.class)
    public void pay_banknotify() {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();

            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            
       
            Order order = OrderDao.dao.findOne(out_trade_no);

            Map<String, String> trade = new HashMap<String, String>();
            trade.put("trade_no", trade_no);
            trade.put("trade_status", trade_status);
            trade.put("time", String.valueOf(new Date().getTime()));

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("TRADE_FINISHED")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
                trade.put("trade_status_message", "买家通过网银支付,付款成功");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
                trade.put("trade_status_message", "买家通过网银支付,付款成功");
                if (order.getStatus() == 1) {
                    OrderDao.dao.set(order, "status", 2);
                    GoodsDao.dao.inc(order.getGoods(), "sales", 1);
                }

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
            tradeList.add(trade);
            
            OrderDao.dao.push(order, "trades",tradeList);
            
        } catch (Exception e) {
            renderText("fail");
        }
    }
    

    @ClearInterceptor(ClearLayer.ALL)
    @Before(POST.class)
    public void payCart_notify() {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();

            Map requestParams = getParaMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }


            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

            //商户订单号
            String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(getPara("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            
//            //商户订单号
//            String out_trade_no = getPara(0);
//            //支付宝交易号
//            String trade_no = "我操你大爷";
//            //交易状态
//            String trade_status = "WAIT_SELLER_SEND_GOODS";

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

//            if (AlipayNotify.verify(params)) {//验证成功
////            	if (true) {//验证成功
//                //////////////////////////////////////////////////////////////////////////////////////////
//                //请在这里加上商户的业务逻辑程序代码
//
//                Cart order = CartDao.dao.findOne(out_trade_no);
//
//                Map<String, String> trade = new HashMap<String, String>();
//                trade.put("trade_no", trade_no);
//                trade.put("trade_status", trade_status);
//                trade.put("time", String.valueOf(new Date().getTime()));
//
//                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//                if (trade_status.equals("WAIT_BUYER_PAY")) {
//                    //该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
//                    trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录，但没有付款");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
//                    //该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
//                    trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货");
////                    if (order.getStatus() == 1) {
////                        CartDao.dao.set(order, "status", 2);
////
////                        for (Order o : order.getOrders()) {
////                            OrderDao.dao.set(o, "status", 2);
////                        }
////                    }
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
//                    //该判断表示卖家已经发了货，但买家还没有做确认收货的操作
//                    trade.put("trade_status_message", "卖家已经发了货，但买家还没有做确认收货的操作");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else if (trade_status.equals("TRADE_FINISHED")) {
//                    //该判断表示买家已经确认收货，这笔交易完成
//                    trade.put("trade_status_message", "买家已经确认收货，这笔交易完成");
//
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    renderText("success");    //请不要修改或删除
//                } else {
//                    renderText("success");    //请不要修改或删除
//                }
//
//                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//                
//                List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
//                tradeList.add(trade);
//                
//                CartDao.dao.push(order, "trades",tradeList);
//
////                CartDao.dao.push(order, "trades", trade);
//                
//                //////////////////////////////////////////////////////////////////////////////////////////
//            } else {//验证失败
//                renderText("fail");
//            }
            
            
            Cart order = CartDao.dao.findOne(out_trade_no);

            Map<String, String> trade = new HashMap<String, String>();
            trade.put("trade_no", trade_no);
            trade.put("trade_status", trade_status);
            trade.put("time", String.valueOf(new Date().getTime()));

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if (trade_status.equals("WAIT_BUYER_PAY")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
                trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录，但没有付款");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
                //该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
                trade.put("trade_status_message", "买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货");
                if (order.getStatus() == 1) {
                    CartDao.dao.set(order, "status", 2);

                    for (Order o : order.getOrders()) {
                        OrderDao.dao.set(o, "status", 2);
                    }
                }

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
                //该判断表示卖家已经发了货，但买家还没有做确认收货的操作
                trade.put("trade_status_message", "卖家已经发了货，但买家还没有做确认收货的操作");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else if (trade_status.equals("TRADE_FINISHED")) {
                //该判断表示买家已经确认收货，这笔交易完成
                trade.put("trade_status_message", "买家已经确认收货，这笔交易完成");

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                renderText("success");    //请不要修改或删除
            } else {
                renderText("success");    //请不要修改或删除
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            
            List<Map<String, String>> tradeList=new ArrayList<Map<String, String>>();
            tradeList.add(trade);
            
            CartDao.dao.push(order, "trades",tradeList);
            
        } catch (Exception e) {
            renderText("fail");
        }
    }
}
