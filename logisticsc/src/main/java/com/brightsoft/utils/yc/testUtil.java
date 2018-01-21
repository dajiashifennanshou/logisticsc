package com.brightsoft.utils.yc;

import java.lang.reflect.Field;
import java.util.Date;


import aj.org.objectweb.asm.Type;

/**
 *Good Luck
 *
*/
public class testUtil<T> {
	/**
	   * 获取分页条件map，作为mapping的参数
	   * Author:FENG
	   * 2016年5月13日
	   * @return
	   */
	  public  void getElestMap(T t){
		  Class c=t.getClass();
		  for(Field f:c.getFields()){
			  System.out.println(f.getName());
		  }
	  }

	  public static void main(String[] args)   {
		  	Object o=new Date();
			System.out.println(Type.getType(o.getClass()));
	}
}
