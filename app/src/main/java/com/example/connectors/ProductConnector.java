package com.example.connectors;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductConnector {
    SQLiteDatabase database;

    public ProductConnector(Activity context) {
        database = context.openOrCreateDatabase("AppDatabase.sqlite", context.MODE_PRIVATE, null);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM Product WHERE CategoryID = ?", new String[]{String.valueOf(categoryId)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String image = cursor.getString(3);
            int catId = cursor.getInt(4);

            Product p = new Product(id, name, price, image, catId);
            list.add(p);
        }
        cursor.close();
        return list;
    }

    public void updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("ProductName", product.getProductName());
        values.put("UnitPrice", product.getUnitPrice());
        values.put("ImageLink", product.getImageLink());
        values.put("CategoryID", product.getCategoryID());

        database.update("Product", values, "productID = ?", new String[]{String.valueOf(product.getProductID())});
    }

    public void deleteProduct(int productId) {
        database.delete("Product", "productID = ?", new String[]{String.valueOf(productId)});
    }
    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("ProductName", product.getProductName());
        values.put("UnitPrice", product.getUnitPrice());
        values.put("ImageLink", product.getImageLink());
        values.put("CategoryID", product.getCategoryID());

        database.insert("Product", null, values);
    }
}
