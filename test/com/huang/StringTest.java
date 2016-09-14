package com.huang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StringTest {
	public void split(){
		String msg = "ksdjflds var mod = \"000000\" ";
		 int i = msg.indexOf("mod") + 7;
		 String result = msg.substring(i, i + 3);
		 if(result.indexOf("\"")==1)
			result = result.substring(0,1);
		 if(result.indexOf("\"")==2)
			 result = result.substring(0,2);
		 
		 System.out.println(result);
	}
	
	@Test
	public void jsonTest(){
		Map<String, String> map = new HashMap<String,String>();
		map.put("key1","value1");
		map.put("key2","value2");
		map.put("key3","value3");
		map.put("key4","value4");
		
		List list = new ArrayList();
		list.add(":");
		list.add("'");
		list.add("/");
		JsonKit.setTimestampPattern("yyyy-MM-dd HH:mm");
		System.out.println(JsonKit.toJson(map));
		System.out.println(JsonKit.toJson(list));
	}
}
