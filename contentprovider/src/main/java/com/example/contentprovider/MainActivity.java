package com.example.contentprovider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor c = getContentResolver().query(Uri.parse("content://com.example.contentproviderexample.MyProvider/cte"),null,null,null,null);

    }


}
