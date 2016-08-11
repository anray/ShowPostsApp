package com.softteq.testappsoftteq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.SoftteqApplication;
import com.softteq.testappsoftteq.data.storage.models.UserDTO;
import com.softteq.testappsoftteq.fragment.PageFragment;
import com.softteq.testappsoftteq.network.response.Posts;
import com.softteq.testappsoftteq.network.response.Users;
import com.softteq.testappsoftteq.network.restmodels.RestService;
import com.softteq.testappsoftteq.network.restmodels.ServiceGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PageFragment.GridItem {

    private static final String TAG = "MainActivity";
    private final static int GRID_SIZE = 6;

    private List<Posts> mPosts = new ArrayList<>();
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private CircleIndicator mCircleIndicator;
    private Button mButton;
    private RestService mRestService;
    private int mPageCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mCircleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        mButton = (Button) findViewById(R.id.save_log_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToLog();
            }
        });


        mRestService = ServiceGenerator.createService(RestService.class);
        Call<List<Posts>> call = mRestService.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.code() == 200) {


                    writeLog(Thread.currentThread().getName().toString());


                    mPosts.addAll(response.body());

                    mPageCount = (int) Math.ceil((float) mPosts.size() / GRID_SIZE);

//                    ArrayAdapter<Posts> mPostsAdapter = new GridAdapter(getApplicationContext(), mPosts);
//                    mGridView = (GridView) findViewById(R.id.grid_gv);
//
//                    mGridView.setAdapter(mPostsAdapter);

                    mPager = (ViewPager) findViewById(R.id.pager);
                    mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                    mPager.setAdapter(mPagerAdapter);
                    mCircleIndicator.setViewPager(mPager);


                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });

    }

    private void saveToLog() {
        if (isExternalStorageWritable()) {

            File appDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
            File logDirectory = new File(appDirectory + "/SOFTTEQ_LOG");

            String fileTitle = "logcat" + System.currentTimeMillis() + ".txt";
            File logFile = new File(logDirectory, fileTitle);


            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile);
                Toast.makeText(getApplicationContext(), getString(R.string.file_saved_message) + fileTitle, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), getString(R.string.saving_failed_message), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onGridItemClicked(final Posts post) {


        Call<Users> call = mRestService.getUser(String.valueOf(post.getUserId()));
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                Users user = response.body();
                UserDTO userToPass = new UserDTO(post, user);

                Intent sendContactDetails = new Intent(MainActivity.this, ContactActivity.class);
                sendContactDetails.putExtra(SoftteqApplication.USER_DETAILS_PARCELABLE, userToPass);
                startActivity(sendContactDetails);

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        //Toast.makeText(getApplicationContext(), String.valueOf(post.getId()), Toast.LENGTH_SHORT).show();


    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ArrayList<Posts> postsChunk;

            int startIndex = GRID_SIZE * position;

            //need this verification because +GRID_SIZE may outbound the size of List
            int endIndex = Math.min(GRID_SIZE * position + GRID_SIZE, mPosts.size());

            writeLog("position: " + position);
            writeLog("mPosts.size(): " + mPosts.size());
            writeLog("endIndex: " + endIndex);

            postsChunk = new ArrayList<>(mPosts.subList(startIndex, endIndex));


            return PageFragment.newInstance(position, postsChunk);
        }

        @Override
        public int getCount() {
            writeLog(mPageCount);
            return mPageCount;
        }

    }

    public static void writeLog(Object message) {
        if (false) {
            Log.d(TAG, message.toString());
        }
    }


}


