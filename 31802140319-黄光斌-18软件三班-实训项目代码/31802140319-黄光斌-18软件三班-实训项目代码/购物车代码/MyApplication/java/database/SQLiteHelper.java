package com.example.administrator.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.myapplication.bean.ProductBean;
import com.example.administrator.myapplication.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public SQLiteHelper(Context context) {
        super(context,DBUtils.DATABASE_NAME,null,DBUtils.DATABASE_VERION);
        sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + DBUtils.DATABASE_TABLE + "(" + DBUtils.PRODUCT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + DBUtils.PRODUCT_NAME +
                " text," + DBUtils.PRODUCT_PRICE + " decimal,"+DBUtils.PRODUCT_SRC+
                " text,"+DBUtils.PRODUCT_ISSHOP+" tinyint(1))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void add(String name,double price,String src,int isshop){
        ContentValues values = new ContentValues();
        values.put(DBUtils.PRODUCT_NAME,name);
        values.put(DBUtils.PRODUCT_PRICE,price);
        values.put(DBUtils.PRODUCT_SRC,src);
        values.put(DBUtils.PRODUCT_ISSHOP,isshop);
        sqLiteDatabase.insert(DBUtils.DATABASE_TABLE,null,values);
    }

    public void delete(String name){
        sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,DBUtils.PRODUCT_NAME+"=?",new String[]{name});
    }

    public void clear(){
        sqLiteDatabase.delete(DBUtils.DATABASE_TABLE,"",new String[]{});
    }

    public void update(String id,int isshop){
        ContentValues values = new ContentValues();
        values.put(DBUtils.PRODUCT_ISSHOP,isshop);
        sqLiteDatabase.update(DBUtils.DATABASE_TABLE,values,DBUtils.PRODUCT_ID+"=?",new String[]{id});
    }

    public List query(int isShop){
        List list = new ArrayList();
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE,null,null,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBUtils.PRODUCT_ISSHOP)))>=isShop){
                    ProductBean noteInfo = new ProductBean();
                    noteInfo.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.PRODUCT_ID))));
                    noteInfo.setSrc(cursor.getString(cursor.getColumnIndex(DBUtils.PRODUCT_SRC)));
                    noteInfo.setName(cursor.getString(cursor.getColumnIndex(DBUtils.PRODUCT_NAME)));
                    noteInfo.setPrice(cursor.getString(cursor.getColumnIndex(DBUtils.PRODUCT_PRICE)));
                    noteInfo.setIsShop(cursor.getString(cursor.getColumnIndex(DBUtils.PRODUCT_ISSHOP)));
                    list.add(noteInfo);
                }
            }

            cursor.close();
        }

        return list;
    }
}
