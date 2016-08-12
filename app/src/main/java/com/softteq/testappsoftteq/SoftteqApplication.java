package com.softteq.testappsoftteq;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.softteq.testappsoftteq.activity.MainActivity;
import com.softteq.testappsoftteq.data.storage.models.DaoMaster;
import com.softteq.testappsoftteq.data.storage.models.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by anray on 11.08.2016.
 */
public class SoftteqApplication extends Application {
    public static final String USER_DETAILS_PARCELABLE = "USER_DETAILS_PARCELABLE";
    private static DaoSession sDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "SoftTeq");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(this);

        MainActivity.writeLog("SoftteqApplication.hello");
    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }
}
