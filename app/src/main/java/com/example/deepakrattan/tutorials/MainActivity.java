package com.example.deepakrattan.tutorials;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    MyLocalService localService;
    boolean flag =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.btngetservicemethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = localService.getRandomNumber();
                Toast.makeText(MainActivity.this,num+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent it = new Intent(MainActivity.this,MyLocalService.class);
        bindService(it,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connection);
    }




    ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyLocalService.ReturnLocalService returnLocalService = (MyLocalService.ReturnLocalService)service;
        localService = returnLocalService.getLocalServiceInstance();
        flag=true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        flag=false;
    }
};
}
