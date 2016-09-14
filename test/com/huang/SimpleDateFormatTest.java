package com.huang;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sun.el.parser.ParseException;

public class SimpleDateFormatTest extends Thread {
    // 时间
    private static String dateString = "2010-10-01 02:10:9";
    // 加入元素
    private static Set<Long> set = Collections.synchronizedSet(new HashSet<Long>());
    // 控制线程退出
    private static AtomicBoolean ok = new AtomicBoolean(true);

    private static SimpleDateFormat format;
    static {
	format = new SimpleDateFormat();
	format.applyPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void run() {
	while (SimpleDateFormatTest.ok.get()) {
	    try {
		set.add(((Date) SimpleDateFormatTest.format.parseObject(dateString)).getTime());
	    } catch (Exception e) {
		// e.printStackTrace();
	    }
	}
    }

    public static void main(String args[]) throws ParseException, InterruptedException {
	// 定义线程池
	ExecutorService pools = Executors.newCachedThreadPool();
	// 加入判断逻辑
	pools.execute(new Runnable() {
	    public void run() {
		try {
		    while (SimpleDateFormatTest.ok.get()) {
			if (SimpleDateFormatTest.set.size() > 2) {
			    System.out.println("size"
				    + SimpleDateFormatTest.set.size());
			    SimpleDateFormatTest.ok.set(false);
			    System.out.println("check error");
			}
			Thread.sleep(1000);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	// 加入线程 2个
	for (int i = 0; i < 2; i++) {
	    pools.execute(new SimpleDateFormatTest());
	}
	// 设置时间
	pools.execute(new Runnable() {
	    public void run() {
		int i = 0;
		try {
		    while (i++ < 10 && SimpleDateFormatTest.ok.get()) {
			System.out.println("check " + i + "s");
			Thread.sleep(1000);
		    }
		    if (SimpleDateFormatTest.ok.get()) {
			SimpleDateFormatTest.ok.set(false);
			System.out.println("check ok");
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	pools.shutdown();
    }
}
