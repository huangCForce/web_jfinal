package com.jfinal.plugin.tablebind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;

/**
 * 自动注册model插件
 */
public class AutoTableBindPlugin extends ActiveRecordPlugin {
	private Logger log = LoggerFactory.getLogger(AutoTableBindPlugin.class);

	private ActiveRecordPlugin arp;
	List<File> classList = new ArrayList<File>();

	public AutoTableBindPlugin(DataSource dataSource, ActiveRecordPlugin arp) {
		super(dataSource);
		this.arp = arp;
		try {
			log.info("start scaning table.....");
			scanModel(AutoTableBindPlugin.class.getResource("/").getFile(),
					"classes/");
			log.info("finish scaning table.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean stop() {
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	private void scanModel(String fileDir, String pre) throws Exception {
		File dir = new File(fileDir);
		listPath(dir, ".class", 0);
		Class clazz = null;
		String className = "";
		Table tb = null;
		for (File classFile : classList) {
			className = getClassName(classFile, pre);
			clazz = Class.forName(className);
			if (clazz.getSuperclass() == Model.class) {
				tb = (Table) clazz.getAnnotation(Table.class);
				if (null == tb) {
					log.error(
							"AutoTablePlugin : Model [{}] cann't auto bind .",
							clazz.getName());
					continue;
				}
				if (StrKit.notBlank(tb.ID())) {
					arp.addMapping(tb.name(), tb.ID(), clazz);
					log.info(
							"AutoTablePlugin : addMapping({}, {},{})",
							new Object[] { tb.name(), tb.ID(), clazz.getName() });
				} else {
					arp.addMapping(tb.name(), clazz);
					log.info("AutoTablePlugin : addMapping({},{})", tb.name(),
							clazz.getName());
				}
			}
		}
	}

	/**
	 * 获取类名
	 * 
	 * @param classFile
	 * @param pre
	 * @return
	 */
	private static String getClassName(File classFile, String pre) {
		String objStr = classFile.toString().replaceAll("\\\\", "/");
		String className;
		className = objStr.substring(objStr.indexOf(pre) + pre.length(),
				objStr.indexOf(".class"));
		if (className.startsWith("/")) {
			className = className.substring(className.indexOf("/") + 1);
		}
		return className.replaceAll("/", ".");
	}

	/**
	 * 查找Model文件
	 * 
	 * @param path
	 * @param type
	 * @param level
	 */
	public void listPath(File path, String type, int level) {
		File files[] = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(type)) {
				classList.add(files[i]);
			}
			if (files[i].isDirectory()) {
				listPath(files[i], type, (level + 1));
			}
		}
	}

}