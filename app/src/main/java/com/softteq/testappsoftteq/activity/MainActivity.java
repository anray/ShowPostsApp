package com.softteq.testappsoftteq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.adapter.PostsAdapter;
import com.softteq.testappsoftteq.network.response.Posts;
import com.softteq.testappsoftteq.network.restmodels.RestService;
import com.softteq.testappsoftteq.network.restmodels.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PostsAdapter mPostsAdapter;
    private GridView mGridView;

    private static final String[] mContacts = {"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Полосатик", "Матроскин", "Лизка", "Томосина",
            "Бегемот", "Чеширский", "Дивуар", "Тигра", "Лаура"};
    private List<Posts> mPosts = new ArrayList<>();


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

                    ArrayAdapter<Posts> mPostsAdapter = new PostsAdapter(getApplicationContext(), mPosts);
                    mGridView = (GridView) findViewById(R.id.grid_gv);
                    
                    mGridView.setAdapter(mPostsAdapter);
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

}


