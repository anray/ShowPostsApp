package com.softteq.testappsoftteq.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.adapter.GridAdapter;
import com.softteq.testappsoftteq.fragment.PageFragment;
import com.softteq.testappsoftteq.network.response.Posts;
import com.softteq.testappsoftteq.network.restmodels.RestService;
import com.softteq.testappsoftteq.network.restmodels.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridAdapter mGridAdapter;
    private GridView mGridView;

    private static final String[] mContacts = {"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Полосатик", "Матроскин", "Лизка", "Томосина",
            "Бегемот", "Чеширский", "Дивуар", "Тигра", "Лаура"};
    private List<Posts> mPosts = new ArrayList<>();

    ViewPager pager;
    PagerAdapter pagerAdapter;

    private static final String TAG = "MainActivity";

    private int mPageCount;
    private final int mGridSize = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//       Thread t = new Thread(new Runnable() {
//            public void run() {
//
//                try {
//                    mPosts =  call.execute().body();
//                } catch (Exception e){
//
//                }
//            }
//        });
//        t.start();


        RestService mRestService = ServiceGenerator.createService(RestService.class);
        Call<List<Posts>> call = mRestService.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.code() == 200) {
                    //Toast.makeText(getApplicationContext(), response.body().get(0).getTitle(), Toast.LENGTH_LONG).show();

                    Log.d("TAG", Thread.currentThread().getName().toString());


                    mPosts.addAll(response.body());

                    mPageCount = (int) Math.ceil((float) mPosts.size() / 6);

//                    ArrayAdapter<Posts> mPostsAdapter = new GridAdapter(getApplicationContext(), mPosts);
//                    mGridView = (GridView) findViewById(R.id.grid_gv);
//
//                    mGridView.setAdapter(mPostsAdapter);

                    pager = (ViewPager) findViewById(R.id.pager);
                    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                    pager.setAdapter(pagerAdapter);

                    pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageSelected(int position) {
                            Log.d(TAG, "onPageSelected, position = " + position);


                        }

                        @Override
                        public void onPageScrolled(int position, float positionOffset,
                                                   int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });


        //mGridViewAdapter = new GridViewAdapter(getApplicationContext(),android.R.layout.simple_list_item_1);

        //while (mThreadFlag.isAlive()){}

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ArrayAdapter<Posts> mPostsAdapter = new PostsAdapter(getApplicationContext(), mPosts);
//                mGridView = (GridView) findViewById(R.id.grid_gv);
//                mGridView.setAdapter(mPostsAdapter);
//            }
//        }, 2000);


    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ArrayList<Posts> postsChunk;

            int startIndex = mGridSize * position;

            //need this verification because +mGridSize may outbound the size of List
            int endIndex = Math.min(mGridSize * position + mGridSize, mPosts.size());

            writeLog("position: " + position);
            writeLog("mPosts.size(): " + mPosts.size());
            writeLog("endIndex: " + endIndex);

            postsChunk = new ArrayList<>(mPosts.subList(startIndex, endIndex));


            return PageFragment.newInstance(position, postsChunk);
        }

        @Override
        public int getCount() {
            //writeLog(mPageCount);


            return mPageCount;
        }

    }

    public static void writeLog(Object message) {
        if (true) {
            Log.d(TAG, message.toString());
        }
    }

}


