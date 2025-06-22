package com.example.connectors;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryConnector {
    SQLiteDatabase database;
    Activity context;

    public CategoryConnector(Activity context) {
        database = context.openOrCreateDatabase("AppDatabase.sqlite", Context.MODE_PRIVATE, null);
    }

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Category", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            list.add(new Category(id, name));
        }
        cursor.close();
        return list;
    }

}
