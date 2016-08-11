package com.softteq.testappsoftteq;

import android.app.Application;

import com.softteq.testappsoftteq.data.storage.models.DaoSession;

/**
 * Created by anray on 11.08.2016.
 */
public class SoftteqApplication extends Application {
    public static final String USER_DETAILS_PARCELABLE = "USER_DETAILS_PARCELABLE";
    private static DaoSession sDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();


    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }
}
