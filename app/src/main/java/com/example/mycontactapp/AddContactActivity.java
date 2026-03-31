package com.example.mycontactapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    Button btnDelete;
    EditText etName, etPhone;
    Button btnSave;
    DatabaseHelper db;

    int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
        btnDelete= findViewById(R.id.btnDelete);

        db = new DatabaseHelper(this);

        if (getIntent().hasExtra("id")) {

            id = getIntent().getIntExtra("id", -1);
            etName.setText(getIntent().getStringExtra("name"));
            etPhone.setText(getIntent().getStringExtra("phone"));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty()) {

                    if (id == -1) {
                        db.addContact(new Contact(name, phone));
                    } else {
                        db.updateContact(new Contact(id, name, phone));
                    }

                    finish(); // go back
                }
            }
        });
        btnDelete.setOnClickListener(v -> {

            if (id != -1) {
                db.deleteContact(id);
            }

            finish();
        });
    }
}