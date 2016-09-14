package com.huang.user;

import com.huang.common.GlobalConstant;
import com.huang.login.LoginUtils;
import com.huang.model.User;
import com.huang.utils.SystemUtils;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.route.ControllerBind;

/**
 * 用户相关的Controller
 */
@ControllerBind(controllerKey = "/user", viewPath = "/WEB-INF/user")
public class UserController extends Controller {
	public static final String NAME_SPACE = "/user";
	public static final String BASE_PATH = "/WEB-INF/user";

	/**
	 * 修改密码
	 */
	public void changepwd() {
		render("changepwd.html");
	}

	/**
	 * 检查用户密码，修改密码时用到
	 */
	public void checkPwd() {
		String password = getPara("password");
		String username = LoginUtils.getLoginUserInfo().getStr("username");
		String _password = LoginUtils.getLoginUserInfo().getStr("password");
		if (_password.equals(SystemUtils.encrypt(password, username))) {
			renderJson(true);
		} else {
			renderJson(false);
		}
	}

	public void savePwd() {
		String password = getPara("password");
		String username = LoginUtils.getLoginUserInfo().getStr("username");
		User user = User.dao.findByUserName(username);
		if (user != null) {
			// 数据库更新
			user.set("password", SystemUtils.encrypt(password, username))
					.update();
			// session更新
			setSessionAttr(GlobalConstant.SESSION_USER, user);
			renderJson(true);
		} else {
			renderJson(false);
		}

	}

	/**
	 * 注册
	 */
	@ActionKey("/register")
	public void register() {
		render("register.html");
	}
}
