package com.example.mycontactapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty()) {

                    db.addContact(new Contact(name, phone));

                    finish(); // go back to main screen
                }
            }
        });
    }
}