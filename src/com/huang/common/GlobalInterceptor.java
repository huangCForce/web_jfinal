package com.huang.common;

import java.util.ArrayList;
import java.util.List;

import com.huang.login.LoginController;
import com.huang.login.LoginUtils;
import com.huang.utils.HttpUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 全局拦截
 * 
 * @author huangchentao
 * @date 2013-08-29
 */
public class GlobalInterceptor implements Interceptor {

    public static final String NAME_SPACE = "";
    public static final String BASE_PATH = "";

    private static List<String> exclude = new ArrayList<String>();

    static {
		exclude.add(GlobalController.NAME_SPACE);
		exclude.add(LoginController.NAME_SPACE);
		exclude.add(LoginController.NAME_SPACE + "/check");
		exclude.add("/register");
    }

    public void intercept(Invocation ai) {
		HttpUtils.init(ai.getController().getRequest(), ai.getController().getResponse());
		_go(ai);
		HttpUtils.clear();
    }

    private void _go(Invocation ai) {
		String url = ai.getController().getRequest().getRequestURI();
		for (String path : exclude) {
		    if (url.equalsIgnoreCase(path)) {
				ai.invoke();
				return;
		    }
		}
		if(LoginUtils.isLogin() == false){
		    ai.getController().redirect(LoginController.NAME_SPACE);
		    return;
		}
		
		ai.invoke();
    }

}
