package com.example.metrobusservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private MyDBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new MyDBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Insert a new user
    public void insertUser(String firstName, String lastName, String cnic, String phoneNumber, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.USER_F_NAME, firstName);
        contentValues.put(MyDBHelper.USER_L_NAME, lastName);
        contentValues.put(MyDBHelper.USER_CNIC, cnic);
        contentValues.put(MyDBHelper.USER_PHONE, phoneNumber);
        contentValues.put(MyDBHelper.USER_EMAIL, email);
        contentValues.put(MyDBHelper.USER_PASSWORD, password);
        contentValues.put(MyDBHelper.USER_SERIAL, generateSerialNumber());

        database.insert(MyDBHelper.TABLE_USERS, null, contentValues);
    }

    // Generate unique serial number
    private String generateSerialNumber() {
        String serialNumberPrefix = "21-ARID-";
        String query = "SELECT MAX(CAST(SUBSTR(" + MyDBHelper.USER_SERIAL + ", 9) AS INTEGER)) FROM " + MyDBHelper.TABLE_USERS;
        Cursor cursor = database.rawQuery(query, null);

        int maxSerialNumber = 0;
        if (cursor.moveToFirst()) {
            maxSerialNumber = cursor.getInt(0);
        }
        cursor.close();

        int newSerialNumber = maxSerialNumber + 1;
        return serialNumberPrefix + String.format("%03d", newSerialNumber);
    }

    // Fetch all users
    public Cursor fetchUsers() {
        String[] columns = new String[] {
                MyDBHelper.USER_ID,
                MyDBHelper.USER_F_NAME,
                MyDBHelper.USER_L_NAME,
                MyDBHelper.USER_CNIC,
                MyDBHelper.USER_PHONE,
                MyDBHelper.USER_EMAIL,
                MyDBHelper.USER_PASSWORD,
                MyDBHelper.USER_SERIAL
        };
        Cursor cursor = database.query(MyDBHelper.TABLE_USERS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Update a user
    public int updateUser(long id, String firstName, String lastName, String cnic, String phoneNumber, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.USER_F_NAME, firstName);
        contentValues.put(MyDBHelper.USER_L_NAME, lastName);
        contentValues.put(MyDBHelper.USER_CNIC, cnic);
        contentValues.put(MyDBHelper.USER_PHONE, phoneNumber);
        contentValues.put(MyDBHelper.USER_EMAIL, email);
        contentValues.put(MyDBHelper.USER_PASSWORD, password);
        return database.update(MyDBHelper.TABLE_USERS, contentValues, MyDBHelper.USER_ID + " = " + id, null);
    }

    // Delete a user
    public void deleteUser(long id) {
        database.delete(MyDBHelper.TABLE_USERS, MyDBHelper.USER_ID + " = " + id, null);
    }

    // Verify user credentials
    public boolean verifyUser(String phoneNumber, String password) {
        String[] columns = { MyDBHelper.USER_ID }; // You can return more columns if needed
        String selection = MyDBHelper.USER_PHONE + " = ? AND " + MyDBHelper.USER_PASSWORD + " = ?";
        String[] selectionArgs = { phoneNumber, password };
        Cursor cursor = database.query(MyDBHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
