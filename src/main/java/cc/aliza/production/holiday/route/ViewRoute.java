package cc.aliza.production.holiday.route;

import cc.aliza.production.holiday.controller.view.AboutController;
import cc.aliza.production.holiday.controller.view.AllController;
import cc.aliza.production.holiday.controller.view.CarController;
import cc.aliza.production.holiday.controller.view.CartController;
import cc.aliza.production.holiday.controller.view.CustomController;
import cc.aliza.production.holiday.controller.view.DetailController;
import cc.aliza.production.holiday.controller.view.GongGaoController;
import cc.aliza.production.holiday.controller.view.HelpController;
import cc.aliza.production.holiday.controller.view.HomeController;
import cc.aliza.production.holiday.controller.view.HotelController;
import cc.aliza.production.holiday.controller.view.JPJDController;
import cc.aliza.production.holiday.controller.view.LineController;
import cc.aliza.production.holiday.controller.view.NewPlayController;
import cc.aliza.production.holiday.controller.view.OrderController;
import cc.aliza.production.holiday.controller.view.PayController;
import cc.aliza.production.holiday.controller.view.TUANGOUController;
import cc.aliza.production.holiday.controller.view.TicketController;
import cc.aliza.production.holiday.controller.view.UserController;
import cc.aliza.production.holiday.controller.view.ZQBSController;
import cc.aliza.production.holiday.controller.view.ZhongQiuController;

import com.jfinal.config.Routes;

/**
 * Created by Jing on 14-2-15.
 */
public class ViewRoute extends Routes {
    @Override
    public void config() {

        add("/detail", DetailController.class);
        add("/order", OrderController.class);
        add("/pay", PayController.class);
        add("/user", UserController.class);

        add("/cart", CartController.class);

        add("/line", LineController.class);
        add("/car", CarController.class);
        add("/hotel", HotelController.class);
        add("/ticket", TicketController.class);
        add("/all", AllController.class);

        add("/custom", CustomController.class);

        //add("/play", PlayController.class);

        add("/play", NewPlayController.class);

        add("/help", HelpController.class);
        
        //精品景点 活动
        add("/jpjd", JPJDController.class);
        
        //足球比赛 活动
        add("/zqbs", ZQBSController.class);
        
        //团购推广
        add("/tuangou", TUANGOUController.class);
        
        //中秋专题
        add("/zhongqiu", ZhongQiuController.class);
        
        add("/about", AboutController.class);
        
        add("/gonggao", GongGaoController.class);

        add("/", HomeController.class);
    }
}
