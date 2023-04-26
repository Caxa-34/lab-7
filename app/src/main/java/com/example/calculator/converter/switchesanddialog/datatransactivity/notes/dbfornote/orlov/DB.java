package com.example.calculator.converter.switchesanddialog.datatransactivity.notes.dbfornote.orlov;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE my_test (my_key TEXT, my_value TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void Insert(String key, String value) {
        String sql = "INSERT INTO my_test VALUES('" + key + "', '" + value + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String Select(String key) {
        String sql = "SELECT my_value FROM my_test WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst()) return cur.getString(0);

        return "(!) NOT FOUND";
    }

    public void Update(String key, String value) {
        String sql = "UPDATE my_test SET my_value = '" + value + "' WHERE my_key='" + key + "';";

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(sql);
    }

    public void Delete(String key) {
        String sql = "DELETE FROM my_test WHERE my_key='" + key + "';";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public boolean SearchKey(String key) {
        String sql = "SELECT EXISTS(SELECT * FROM my_test WHERE my_key = '" + key + "' LIMIT 1);";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cur = db.rawQuery(sql, null);
        cur.moveToFirst();

        if(cur.getInt(0) == 1) {
            cur.close();
            return true;
        }
        cur.close();
        return false;
    }
}
