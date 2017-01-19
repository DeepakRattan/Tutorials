package com.example.userpovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    EditText edtid, edtname;
    Button btnok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtid = (EditText) findViewById(R.id.edtid);
        edtname = (EditText) findViewById(R.id.edtname);
        btnok = (Button) findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("pid", Integer.parseInt(edtid.getText().toString().trim()));
                cv.put("pname", edtname.getText().toString().trim());
                getContentResolver().insert(Uri.parse("content://com.example.contentprovider.MyProvider"), cv);
            }
        });

        findViewById(R.id.btnselect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }

    class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            Cursor c = getContentResolver().query(Uri.parse("content://com.example.contentprovider.MyProvider/mydb"), new String[]{"pid", "pname"}, null, null, null);
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("pid"));
                String name = c.getString(c.getColumnIndex("pname"));
                Log.e("Id   ", id + "");
                Log.e("Name    ", name);
            } while (c.moveToNext());

            return null;
        }
    }

}
