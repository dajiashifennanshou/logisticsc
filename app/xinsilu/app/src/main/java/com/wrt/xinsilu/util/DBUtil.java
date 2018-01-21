/**
 * Copyright (C)2014 android Source
 * Chengdu HeChuang Technology Co., Ltd.
 */
package com.wrt.xinsilu.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * <p>
 *	描述
 * </p>
 * 数据库工具类，创建Sqlite数据库
 * 
 * @author wangsong
 */
public class DBUtil extends SQLiteOpenHelper {
	/** 数据库类 */
	private static SQLiteDatabase db;
	/** 数据库名称*/
	private static final String dbName = "Test";
	/** 版本号 */
	private static final int dbVersion = 1;
	/** Context */
	private static Context context;

	private DBUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		db = this.getWritableDatabase();
	}

	/**
	 * 获取数据库
	 * @param context
	 * Context
	 * @return SQLiteDatabase
	 */
	public static SQLiteDatabase getDB(Context context) {
		if (null == db) {
			DBUtil.context = context;
			new DBUtil(context, dbName, null, dbVersion);
		}
		return db;
	}

	/**
	 * 获取数据库
	 */
	public static SQLiteDatabase getDB() {
		return getDB(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists(" +
				"_id Integer primary key," +
				"name" + //用于保存主页的GridView文字信息
				");";
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("verSion--------", oldVersion+"oldVersion--------------");
		Log.i("verSion--------", newVersion+"newVersion--------------");

	}
	/**
	* 检查数据表中是否
	* @param tableName 表明
	* @param columnName 字段名
	* @return 有该字段就返回true，没有返回false
	*/
	private boolean checkColumnExist1(SQLiteDatabase db, String tableName
	        , String columnName) {
	    boolean result = false ;
	    Cursor cursor = null ;
	    try{
	        //查询一行
	        cursor = db.rawQuery( "SELECT * FROM " + tableName + " LIMIT 0"
	            , null );
			//查看该表中是否有该字段
	        result = cursor != null && cursor.getColumnIndex(columnName) != -1 ;
	    }catch (Exception e){
	         Log.e("TAG","checkColumnExists1..." + e.getMessage()) ;
	    }finally{
	        if(null != cursor && !cursor.isClosed()){
	            cursor.close() ;
	        }
	    }

	    return result ;
	}
}
