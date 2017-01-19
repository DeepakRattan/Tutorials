package com.example.deepakrattan.tutorials;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by Pankaj on 8/13/2015.
 */
public class MyLocalService extends Service{
    public IBinder iBinder = new ReturnLocalService();
    Random random = new Random();
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    class ReturnLocalService extends Binder{
        public MyLocalService getLocalServiceInstance(){
            return MyLocalService.this;
        }
    }

    public int getRandomNumber(){
    return random.nextInt(100);
    }
}
