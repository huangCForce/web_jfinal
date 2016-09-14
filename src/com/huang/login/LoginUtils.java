package com.huang.login;

import com.huang.common.GlobalConstant;
import com.huang.user.User;
import com.huang.utils.HttpUtils;
import com.huang.utils.SystemUtils;

public class LoginUtils {

    /**
     * 判断是否登录，true-登录 false-未登录
     */
    public static boolean isLogin() {
	return getLoginUserInfo() != null;
    }

    /**
     * 获取当前登录人信息
     */
    public static User getLoginUserInfo() {
	User user = (User) HttpUtils.getSession(GlobalConstant.SESSION_USER);
	if (user == null) {
	    String _username = HttpUtils.getCookieValue(GlobalConstant.SESSION_USER);
	    user = User.dao.findByUserName(SystemUtils.decrypt(_username));
	}
	return user;
    }
}
