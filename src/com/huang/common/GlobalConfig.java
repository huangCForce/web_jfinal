package com.huang.common;

import com.huang.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.route.AutoBindRoutes;

/**
 * 全局配置
 * 
 * @author huangchentao
 * @date 2013-09-05
 */
public class GlobalConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants constants) {
		PropKit.use("db.properties");
		constants.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes routes) {
		// 添加自动扫描路由的插件，之后新添加的Controller只需在自己类中注明地址，而不再需要添加到这里来
		routes.add(new AutoBindRoutes());
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("db.url"),
				PropKit.get("db.username"), PropKit.get("db.password"),
				PropKit.get("db.driver"));
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins plugins) {
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		plugins.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		plugins.add(arp);
		
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
		
		
//		plugins.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors interceptions) {
		interceptions.add(new GlobalInterceptor());
	}

	/**
	 * 配置全局处理器
	 */
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler(GlobalConstant.CONTEXT_PATH));
	}

	/**
	 * 初使化完成调用
	 */
	public void afterJFinalStart() {
	}

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		com.jfinal.core.JFinal.start("WebRoot", 8088, "/", 5);
	}
}
