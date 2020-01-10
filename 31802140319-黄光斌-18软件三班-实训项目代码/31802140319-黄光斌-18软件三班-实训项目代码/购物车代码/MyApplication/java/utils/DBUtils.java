package com.example.administrator.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtils {

    public static final String DATABASE_NAME = "shopping";
    public static final String DATABASE_TABLE = "shopping";
    public static final int DATABASE_VERION = 1;
    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_SRC = "src";
    public static final String PRODUCT_ISSHOP = "isshop";

    public static final String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
