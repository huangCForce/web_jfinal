package com.huang.login;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.tablebind.Table;

/**
 * 登录日志
 * @author huangchentao
 * @date Sep 15, 2013
 */
@Table(name = "history_login", ID = "id")
public class Login extends Model<Login> {

    private static final long serialVersionUID = 2146750837445878936L;

    public static final Login dao = new Login();
}
