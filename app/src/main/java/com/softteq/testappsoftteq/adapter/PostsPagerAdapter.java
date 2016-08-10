package com.softteq.testappsoftteq.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.network.response.Posts;

import java.util.List;

/**
 * Created by anray on 09.08.2016.
 */
public class PostsPagerAdapter extends PagerAdapter {






    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);




    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }


}
