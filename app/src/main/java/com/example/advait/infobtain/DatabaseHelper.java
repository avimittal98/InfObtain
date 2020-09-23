package com.example.advait.infobtain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "FULL_NAME";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "WEBSITE";
    public static final String COL_7 = "COMPANY";
    public static final String COL_8 = "DESIGNATION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT UNIQUE NOT NULL,PASSWORD TEXT NOT NULL,FULL_NAME TEXT NOT NULL,PHONE INTEGER UNIQUE NOT NULL,WEBSITE TEXT, COMPANY TEXT, DESIGNATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String email,String pass,String name,String no, String web, String comp, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,pass);
        contentValues.put(COL_4,name);
        contentValues.put(COL_5,no);
        contentValues.put(COL_6,web);
        contentValues.put(COL_7,comp);
        contentValues.put(COL_8,designation);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String email,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where EMAIL='"+email+"' AND PASSWORD='"+pass+"'",null);
        return res;
    }
    public Cursor getAllData1(String email,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select * from "+TABLE_NAME+" where EMAIL='"+email+"'",null);
        return res1;
    }

    public boolean updateData(String email, String name,String no, String web, String comp, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4,name);
        contentValues.put(COL_5,no);
        contentValues.put(COL_6,web);
        contentValues.put(COL_7,comp);
        contentValues.put(COL_8,designation);
        db.update(TABLE_NAME, contentValues, "EMAIL = ?",new String[] { email});

        return true;
    }
}
