package com.example.metrobusservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="MetroDB";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_USERS="users";
    public static final String USER_F_NAME="first_name";
    public static final String USER_L_NAME="last_name";
    public static final String USER_CNIC="cnic";
    public static final String USER_PHONE="phone";
    public static final String USER_EMAIL="email";
    public static final String USER_PASSWORD="password";
    public static final String USER_ID="user_id";
    public static final String USER_SERIAL = "serial_number";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_USERS+ "(" +USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_F_NAME+
                " TEXT,"+USER_L_NAME+" TEXT,"+USER_CNIC+" INTEGER,"+USER_PHONE+" INTEGER,"+USER_EMAIL+" TEXT,"+
                USER_PASSWORD + " TEXT," +
                USER_SERIAL + " TEXT UNIQUE);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
