package com.softteq.testappsoftteq.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.SoftteqApplication;
import com.softteq.testappsoftteq.data.storage.models.DaoMaster;
import com.softteq.testappsoftteq.data.storage.models.DaoSession;
import com.softteq.testappsoftteq.data.storage.models.User;
import com.softteq.testappsoftteq.data.storage.models.UserDTO;
import com.softteq.testappsoftteq.data.storage.models.UserDao;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    
    private TextView mPostId;
    private TextView mFullName;
    private TextView mNickName;
    private TextView mEmail;
    private TextView mWebSite;
    private TextView mPhone;
    private TextView mCity;
    private Button mSave;

    private UserDTO mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        mPostId = (TextView) findViewById(R.id.post_id_tv);
        mFullName = (TextView) findViewById(R.id.full_name_tv);
        mNickName = (TextView) findViewById(R.id.nick_name_tv);
        mEmail = (TextView) findViewById(R.id.email_tv);
        mWebSite = (TextView) findViewById(R.id.web_site_tv);
        mPhone = (TextView) findViewById(R.id.phone_tv);
        mCity = (TextView) findViewById(R.id.city_tv);
        mSave = (Button) findViewById(R.id.save_to_db_btn);
        mEmail.setOnClickListener(this);
        mWebSite.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mCity.setOnClickListener(this);
        mSave.setOnClickListener(this);


        mUserInfo = getIntent().getParcelableExtra(SoftteqApplication.USER_DETAILS_PARCELABLE);

        //set User Id in Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.toolbar_contact_text) + String.valueOf(mUserInfo.getUserId()));
        }

        mPostId.setText(String.valueOf(
                mUserInfo.getPostId()));
        mFullName.setText(mUserInfo.getFullName());
        mNickName.setText(mUserInfo.getNickName());
        mEmail.setText(mUserInfo.getEmail());
        mWebSite.setText(mUserInfo.getWebSite());
        mPhone.setText(mUserInfo.getPhone());
        mCity.setText(mUserInfo.getCity());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_tv:
                sendEmail(mEmail.getText().toString());
                break;
            case R.id.web_site_tv:
                viewInBrowser(mWebSite.getText().toString());
                break;
            case R.id.phone_tv:
                dial(mPhone.getText().toString());
                break;
            case R.id.city_tv:
                openMap(mUserInfo.getLatitude(), mUserInfo.getLongitude());
                break;
            case R.id.save_to_db_btn:
                new DbSaver().execute();
                break;


        }

    }

    private void dial(String phoneNo) {

        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNo));
        startActivity(Intent.createChooser(dialIntent, getString(R.string.chooser_title_dial)));
    }

    private void sendEmail(String email) {

        Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO);
        //, Uri.parse("mailto:" + email)); выдает очень много вариантов
        //sendEmailIntent.setType("message/rfc822"); выдает меньше вариантов, но среди них тоже есть не почтовые клиенты
        sendEmailIntent.setData(Uri.parse("mailto:"));
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        startActivity(Intent.createChooser(sendEmailIntent, getString(R.string.chooser_title_sendEmail)));

    }

    private void viewInBrowser(String link) {

        Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + link));
        startActivity(Intent.createChooser(openBrowser, getString(R.string.chooser_title_openBrowser)));
    }

    private void openMap(String latitude, String longitude) {
        //b - это bearing=0, чтобы компас указывал на север
        String uri = "geo:" + latitude + "," + longitude + "?z=6&b=0";
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(Intent.createChooser(openMapIntent, getString(R.string.chooser_title_openMap)));
    }

    class DbSaver extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            saveUserToDb();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //tvInfo.setText("End");
            Toast.makeText(getApplicationContext(), R.string.saved_to_db, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserToDb() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "SoftTeq");
        Database db = helper.getWritableDb();
        DaoSession sDaoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(getApplicationContext());

        UserDao mUserDao = sDaoSession.getUserDao();


        List<UserDTO> userFromParcelable = new ArrayList<>();
        userFromParcelable.add(mUserInfo);
        List<User> userForSave = new ArrayList<>();


        for (UserDTO user : userFromParcelable) {

            userForSave.add(new User(user));
        }

        mUserDao.insertOrReplaceInTx(userForSave);
    }
}
