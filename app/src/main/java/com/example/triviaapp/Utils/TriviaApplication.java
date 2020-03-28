package com.example.triviaapp.Utils;

import android.app.Application;
import android.content.Context;

public class TriviaApplication extends Application {

    public static Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();

        ctx = getApplicationContext();
    }

}
