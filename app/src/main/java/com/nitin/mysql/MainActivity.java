package com.nitin.mysql;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nameET;
    Button insertBtn;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        insertBtn = findViewById(R.id.insertBtn);
        listView = findViewById(R.id.myList);
    }
    public void insertBtnClick(View view) {
        DbHelper dbHelper = new DbHelper(this);
        ArrayList<String> arrayList = new ArrayList<>();
        String name = nameET.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            String msg = dbHelper.create(name);
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
        Cursor cursor = dbHelper.read();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.name, arrayList);
        listView.setAdapter(adapter);
    }
}
