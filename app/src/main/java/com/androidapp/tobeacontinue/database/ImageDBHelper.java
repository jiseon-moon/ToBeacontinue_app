package com.androidapp.tobeacontinue.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class ImageDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;              //Database Version
    private static final String DATABASE_NAME = "imageDB";      //Database Name
    private static final String DB_TABLE = "table_image";       //Table Name


    public ImageDBHelper(@Nullable Context context) {           //생성자 정의
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void queryData(String sql){                          //데이터 쓰기
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertImage(byte[] image){                      //이미지 삽입
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO " + DB_TABLE + " VALUES(?);"; //INSERT INTO imageDB VALUES(image);
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindBlob(1,image);                            //image는 blob 타입으로 저장

        statement.executeInsert();
    }

    public Cursor getData(String sql){                          //데이터 읽기
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }


}
