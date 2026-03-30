package com.example.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "contacts_db";
    private static final int DATABASE_VERSION = 1;

    // Table info
    private static final String TABLE_NAME = "contacts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PHONE + " TEXT" +
                        ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 🔹 CREATE (Add Contact)
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // 🔹 READ (Get all contacts)
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                contact.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));

                list.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    // 🔹 UPDATE
    public void updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());

        db.update(TABLE_NAME, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    // 🔹 DELETE
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});

        db.close();
    }
}