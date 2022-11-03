package com.example.ead_2022_a1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{

    //database name
    public static final String DATABASE_NAME = "ead_2022_a1.db";

    //constructor
    public DBHelper(@Nullable Context context) {
        super(context, "ead_2022_a1.db", null, 1);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT , usertype TEXT)");
    }

    //onUpgrade is called when the database version is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

    }

    //insert data
    public boolean insert(String username, String password , String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("userType", type);
        long ins = db.insert("user", null, contentValues);
        if(ins == -1) return false;
        else return true;
    }

    //check if username exists
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0) return false;
        else return true;
    }

    //check if username and password exists
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    //get user type
    public String getUserType(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});
        String type = "";
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                type = cursor.getString(3);
            }
        }
        return type;
    }
}
