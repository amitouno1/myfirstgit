package cc.aliza.production.holiday.controller.view;

import cc.aliza.production.holiday.interceptor.view.DataInterceptor;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created by Jing on 14-2-8.
 */
@Before({DataInterceptor.class})
public class GongGaoController extends Controller {

    public void index() {

        render("/view/gonggao.html");

    }

}
