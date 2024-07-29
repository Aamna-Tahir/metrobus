package com.example.metrobusservice;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        listView = findViewById(R.id.listview);
        dbManager = new DatabaseManager(this);
        dbManager.open();

        List<User> userList = fetchUsers();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(adapter);

        dbManager.close();
    }

    private List<User> fetchUsers() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = dbManager.fetchUsers();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String firstName = cursor.getString(cursor.getColumnIndex(MyDBHelper.USER_F_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(MyDBHelper.USER_L_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(MyDBHelper.USER_PHONE));

                userList.add(new User(firstName, lastName, phoneNumber));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return userList;
    }
}
