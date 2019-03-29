package com.mashtion.sqlitetestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "student.db";
    public final static String TABLE_NAME = "studentTable";
    public final static String COLUMN_1 = "ID";
    public final static String COLUMN_2 = "Name";
    public final static String COLUMN_3 = "Surname";
    public final static String COLUMN_4 = "Marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, SURNAME TEXT, MARKS INTEGER ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String Name, String Surname, String  Mark){
        SQLiteDatabase db = getWritableDatabase(); //this will create the database and the table
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2 , Name);
        contentValues.put(COLUMN_3 , Surname);
        contentValues.put(COLUMN_4 , Mark);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if (result == -1 ){
            return false;
        }else return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = null ;
        result = db.rawQuery("SELECT * FROM "+ TABLE_NAME , null);
        return result;

    }

    public boolean updateData(String Id, String Name, String Surname, String Mark){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, Id);
        contentValues.put(COLUMN_2 , Name);
        contentValues.put(COLUMN_3 , Surname);
        contentValues.put(COLUMN_4 , Mark);
        long result = db.update(TABLE_NAME,contentValues,"ID = ?", new String[] {Id});
       //return true;
        if(result == 0){
            return false;
        }else {
            return true;
        }
    }
    public int deleteData(String id){
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME,"ID = ? ", new String[]  { id });
        return result;
    }
}











