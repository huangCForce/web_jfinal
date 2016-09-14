package com.huang.common;

import com.huang.main.MainController;
import com.jfinal.core.Controller;
import com.jfinal.route.ControllerBind;

/**
 * 全局控制
 * 
 * @author huangchentao
 * @date 2013-08-29
 */
@ControllerBind(controllerKey = "/", viewPath = "/WEB-INF")
public class GlobalController extends Controller {
    public static final String NAME_SPACE = "/";
    public static final String BASE_PATH = "/WEB-INF";

    public void index() {
		redirect(MainController.NAME_SPACE);
    }

}
