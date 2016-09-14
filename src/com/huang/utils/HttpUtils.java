package com.huang.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
    /** 当前请求的request */
    private static final ThreadLocal<HttpServletRequest> THREAD_REQUST = new ThreadLocal<HttpServletRequest>();
    /** 当前请求的response */
    private static final ThreadLocal<HttpServletResponse> THREAD_RESPONSE = new ThreadLocal<HttpServletResponse>();

    /**
     * 初始化request和response对象
     */
    public static void init(HttpServletRequest request,
	    HttpServletResponse response) {
	THREAD_REQUST.set(request);
	THREAD_RESPONSE.set(response);
    }

    /**
     * 清除request和response对象
     */
    public static void clear() {
	THREAD_REQUST.remove();
	THREAD_RESPONSE.remove();
    }

    /**
     * 获取request对象
     */
    public static HttpServletRequest getRequest() {
	return THREAD_REQUST.get();
    }

    /**
     * 获取response对象
     */
    public static HttpServletResponse getResponse() {
	return THREAD_RESPONSE.get();
    }

    /**
     * return session
     */
    public static Object getSession(String key) {
	return getRequest().getSession().getAttribute(key);
    }

    /**
     * 获取cookies中值
     */
    public static String getCookieValue(String name) {
	Cookie cookie = getCookie(name);
	return cookie == null ? null : cookie.getValue();
    }

    /**
     * Return a cookie.
     */
    private static Cookie getCookie(String name) {
	Cookie[] cookies = getRequest().getCookies();
	if (cookies == null) {
	    return null;
	}
	for (Cookie cookie : cookies) {
	    if (cookie.getName().equals(name)) {
		return cookie;
	    }
	}
	return null;
    }

}
