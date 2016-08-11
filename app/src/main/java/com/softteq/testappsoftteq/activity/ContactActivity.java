package com.softteq.testappsoftteq.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.SoftteqApplication;
import com.softteq.testappsoftteq.data.storage.models.UserDTO;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mUserId;
    private TextView mPostId;
    private TextView mFullName;
    private TextView mNickName;
    private TextView mEmail;
    private TextView mWebSite;
    private TextView mPhone;
    private TextView mCity;

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
        mEmail.setOnClickListener(this);
        mWebSite.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mCity.setOnClickListener(this);


        UserDTO userInfo = getIntent().getParcelableExtra(SoftteqApplication.USER_DETAILS_PARCELABLE);

        getSupportActionBar().setTitle(getString(R.string.toolbar_contact_text) + String.valueOf(userInfo.getUserId()));

        mPostId.setText(String.valueOf(
                userInfo.getPostId()));
        mFullName.setText(userInfo.getFullName());
        mNickName.setText(userInfo.getNickName());
        mEmail.setText(userInfo.getEmail());
        mWebSite.setText(userInfo.getWebSite());
        mPhone.setText(userInfo.getPhone());
        mCity.setText(userInfo.getCity());


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

                break;

        }

    }

    private void dial(String phoneNo) {

        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNo));
        startActivity(Intent.createChooser(dialIntent, getString(R.string.chooser_title_dial)));
    }

    private void sendEmail(String email) {

        Intent dialIntent = new Intent(Intent.ACTION_SENDTO);
        //, Uri.parse("mailto:" + email)); выдает очень много вариантов
        //dialIntent.setType("message/rfc822"); выдает меньше вариантов, но среди них тоже есть не почтовые клиенты
        dialIntent.setData(Uri.parse("mailto:"));
        dialIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        startActivity(Intent.createChooser(dialIntent, getString(R.string.chooser_title_sendEmail)));

    }

    private void viewInBrowser(String link) {

        Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + link));
        startActivity(Intent.createChooser(openBrowser, getString(R.string.chooser_title_openBrowser)));
    }
}
