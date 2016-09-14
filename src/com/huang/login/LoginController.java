package com.huang.login;

import com.huang.common.GlobalConstant;
import com.huang.main.MainController;
import com.huang.model.User;
import com.huang.utils.SystemUtils;
import com.jfinal.core.Controller;
import com.jfinal.route.ControllerBind;

/**
 * 登录相关Controller
 */
@ControllerBind(controllerKey = "/login", viewPath = "/WEB-INF/login")
public class LoginController extends Controller {
	public static final String NAME_SPACE = "/login";
	public static final String BASE_PATH = "/WEB-INF/login";

	public void index() {
		render("login.html");
	}

	/**
	 * 登录检查用户名密码
	 */
	public void check() {
		String username = getPara("username");
		String password = getPara("password");
		String remember = getPara("remember");
		User user = User.dao.findByUserName(username);
		if (user != null
				&& user.getStr("password").equals(
						SystemUtils.encrypt(password, username))) {
			// 把用户名加密后放入cookie中
			String _username = SystemUtils.encrypt(username);
			if (remember != null) {
				// 勾选记住我功能，把username值存入cookie保存在浏览器端，保存期限为10天
				setCookie(GlobalConstant.SESSION_USER, _username,
						60 * 60 * 24 * 10);
			} else {
				// 设置浏览器关闭即清理cookie
				setCookie(GlobalConstant.SESSION_USER, _username, 0);
			}
			setSessionAttr(GlobalConstant.SESSION_USER, user);
			redirect(MainController.NAME_SPACE);
		} else {
			setAttr("errMsg", "1");
			setAttr("username", username);
			index();
		}
	}

	/**
	 * 注销
	 */
	public void logout() {
		removeSessionAttr(GlobalConstant.SESSION_USER);
		removeCookie(GlobalConstant.SESSION_USER);
		index();
	}

	/**
	 * 添加登录记录
	 */
	public void addLoginRecord() {

	}
}
