package com.example.calculator.converter.switchesanddialog.datatransactivity.notes.dbfornote.orlov;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etKey, etValue;
    DB myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etKey = findViewById(R.id.etKey);
        etValue = findViewById(R.id.etValue);
        myDb = new DB(this, "mybase.db", null, 1);
    }

    public void insert(View v) {
        String key = etKey.getText().toString(),
        value = etValue.getText().toString();
        if (myDb.SearchKey(key)) {
            Toast.makeText(this, "Запись уже существует", Toast.LENGTH_SHORT).show();
            return;
        }
        myDb.Insert(key, value);
    }

    public void update(View v) {
        String key = etKey.getText().toString(),
        value = etValue.getText().toString();

        myDb.Update(key, value);
    }

    public void select(View v) {
        String key = etKey.getText().toString();
        String value = myDb.Select(key);
        etValue.setText(value);
    }

    public void delete(View v) {
        String key = etKey.getText().toString();
        if (!myDb.SearchKey(key)) {
            Toast.makeText(this, "Записи не существует", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            bld.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    myDb.Delete(key);
                }
            });
            AlertDialog dlg = bld.create();
            dlg.setMessage("Are you sure?");
            dlg.setTitle("Delete record");
            dlg.show();
        }
    }
}