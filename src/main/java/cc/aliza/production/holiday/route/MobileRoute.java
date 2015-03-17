package cc.aliza.production.holiday.route;

import cc.aliza.production.holiday.controller.mobile.mainController;

import com.jfinal.config.Routes;

public class MobileRoute  extends Routes {

	@Override
    public void config() {

        add("/mobile", mainController.class);
        
   
    }
	
}
