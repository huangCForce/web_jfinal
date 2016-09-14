package com.huang.user;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.tablebind.Table;

@Table(name = "user", ID = "id")
public class User extends Model<User> {
    private static final long serialVersionUID = -6631375682062772953L;
    public static final User dao = new User();

    public List<User> findAll() {
    	return dao.findAll();
    }

    public User findByUserName(String username) {
    	if (StrKit.isBlank(username))
		    return null;
		String sql = "select * from user where username = ?";
		return dao.findFirst(sql, username);
    }
    
}
