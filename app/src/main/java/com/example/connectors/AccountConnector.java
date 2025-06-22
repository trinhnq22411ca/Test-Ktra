package com.example.connectors;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.Account;

public class AccountConnector {
    Activity context;
    String DATABASE_NAME="AppDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    public AccountConnector(Activity context) {
        this.context = context;
    }
    public Account login(String usr, String pwd) {
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        Cursor cursor = database.rawQuery(
                "SELECT * FROM Account WHERE Username = ? COLLATE NOCASE AND Password = ?",
                new String[]{usr, pwd});

        Account account = null;
        if (cursor.moveToNext()) {
            int UserID = cursor.getInt(0);
            String Username = cursor.getString(1);
            String Password = cursor.getString(2);
            int SaveInfo = cursor.getInt(3);

            account=new Account();

            account.setUsername(Username);
            account.setPassword(Password);
            account.setSaveInfo(SaveInfo==1?true:false);
        }

        cursor.close();
        return account;
    }
}
