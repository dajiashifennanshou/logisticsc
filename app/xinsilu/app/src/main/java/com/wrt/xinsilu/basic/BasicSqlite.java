package com.wrt.xinsilu.basic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wrt.xinsilu.util.DBUtil;


/**
 * 所有的数据库表操作文件都需继承该类
 * Created by Constandine on 2016/6/1.
 */
public class BasicSqlite {
    /**数据库对象*/
    protected SQLiteDatabase db;
    /**上下文对象*/
    protected Context context;
    public BasicSqlite(Context context){
        db= DBUtil.getDB(context);
        this.context=context;
    }
    /**
     * 关闭游标
     * @param cursor Cursor
     */
    protected void closeCursor(Cursor cursor){
        if(cursor!=null&&!cursor.isClosed()){
            cursor.close();
        }
    }
}
