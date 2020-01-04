package com.example.firstassignment;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="SQLite>>>>>";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlcIn="create table userbrowsecourse(coursename varchar(255),coursecode varchar(10),coursestatus varchar(255))";
        String sqlcyl="create table userbrowseteacher(teachername varchar(255))";
        db.execSQL(sqlcIn);
        db.execSQL(sqlcyl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"数据库更新成功");
    }
}
