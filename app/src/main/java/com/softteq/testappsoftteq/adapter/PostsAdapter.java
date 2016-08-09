package com.softteq.testappsoftteq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.network.response.Posts;

import java.util.List;

/**
 * Created by anray on 08.08.2016.
 */
public class PostsAdapter extends ArrayAdapter<Posts> {

    //private Posts[] mPosts;
    private static final String[] mContacts = { "Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Полосатик", "Матроскин", "Лизка", "Томосина",
            "Бегемот", "Чеширский", "Дивуар", "Тигра", "Лаура" };

    private Context mContext;
    private List<Posts> mPosts;

    public PostsAdapter(Context context, List<Posts> posts) {
        super(context, android.R.layout.simple_list_item_2, posts);
        mContext = context;
        mPosts = posts;
    }


    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Posts getItem(int position) {
        return mPosts.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Posts post = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2,null);

        }



        ((TextView) convertView.findViewById(android.R.id.text1)).setText(String.valueOf(post.getId()));

        ((TextView) convertView.findViewById(android.R.id.text2)).setMaxLines(1);
        ((TextView) convertView.findViewById(android.R.id.text2)).setText(post.getTitle());

        return convertView;
    }


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //return super.getView(position, convertView, parent);
//
//        TextView label = (TextView) convertView;
//
//        if (convertView == null) {
//            convertView = new TextView(mContext);
//            label = (TextView) convertView;
//        }
//        label.setText(mContacts[position]);
//        return (convertView);
//
//    }

}
