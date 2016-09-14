package com.huang.common;

/**
 * 全局常量
 * 
 * @author huangchentao
 * @date 2013-08-29
 */
public class GlobalConstant {
    /** 加盐 */
    public static final String CRYPT_KEY = "_HUANGCT";
    /** Model名称 */
    public static final String BEAN_KEY = "bean";
    /** 传送到页面的Page对象 */
    public static final String BEANS_KEY = "beans";
    /** 上下文路径标识 */
    public static final String CONTEXT_PATH = "base";
    // =================分页相关======================
    /** 当前页面 */
    public static final String PAGE_NUM_KEY = "pageNum";
    /** 默认第一页 */
    public static final int PAGE_NUM_DEFAULT = 1;
    /** 每页显示记录数 */
    public static final String PAGE_SIZE_KEY = "pageSize";
    /** 默认每页显示20条数据 */
    public static final int PAGE_SIZE_DEFAULT = 20;
    /** 排序字段名 */
    public static final String PAGE_ORDER_FIELD_KEY = "orderField";
    /** 默认排序字段名ID */
    public static final String PAGE_ORDER_FIELD_DEFAULT = "ID";
    /** 排序方向 */
    public static final String PAGE_ORDER_DIRECTION_KEY = "orderDirection";
    /** 默认的排序方向 */
    public static final String PAGE_ORDER_DIRECTION_DEFAULT = "ASC";

    /** 保存已登录用户信息的session名字 */
    public static final String SESSION_USER = "SESSION_USER";

}
