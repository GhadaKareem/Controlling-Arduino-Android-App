package com.led;

/**
 * Created by Ahmed Shawkat on 8/8/2018.
 */

import android.provider.BaseColumns;

public class database {

    public database()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {

        public static final String USER_NAME = "user_name" ;
        public static final String USER_PASS = "user_pass" ;
        public static final String DATABASE_NAME = "userdb97";
        public static final String TABLE_NAME = "regtb97";

    }}
