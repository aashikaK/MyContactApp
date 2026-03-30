package com.example.mycontactapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> contactList;
    DatabaseHelper db;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        contactList = db.getAllContacts();

        adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddContactActivity.class));
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        contactList.clear();
        contactList.addAll(db.getAllContacts());
        adapter.notifyDataSetChanged();
    }
}