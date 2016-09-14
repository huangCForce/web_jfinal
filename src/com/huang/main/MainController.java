package com.huang.main;

import com.huang.login.LoginUtils;
import com.huang.user.User;
import com.jfinal.core.Controller;
import com.jfinal.route.ControllerBind;

/**
 * 登录后的主界面
 * 
 * @author huangchentao
 * @date Sep 13, 2013
 */
@ControllerBind(controllerKey = "/main", viewPath = "/WEB-INF/main")
public class MainController extends Controller {
	public static final String NAME_SPACE = "/main";
	public static final String BASE_PATH = "/WEB-INF/main";

	public void index() {
		User user = LoginUtils.getLoginUserInfo();
		if (user == null)
			user = new User();
		setAttr("user", user);
		render("main.html");
	}

}
