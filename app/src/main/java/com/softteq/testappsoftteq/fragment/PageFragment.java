package com.softteq.testappsoftteq.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.softteq.testappsoftteq.R;
import com.softteq.testappsoftteq.adapter.GridAdapter;
import com.softteq.testappsoftteq.network.response.Posts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anray on 10.08.2016.
 */
public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_DATA = "arg_data";
    private GridItem mGridItemListener;

    int pageNumber;
    List<Posts> mPosts;


    public static PageFragment newInstance(int page, ArrayList<Posts> posts) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putParcelableArrayList(ARGUMENT_DATA, posts);
        pageFragment.setArguments(arguments);


        //mPosts = posts;
        return pageFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        mPosts = getArguments().getParcelableArrayList(ARGUMENT_DATA);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);

        ArrayAdapter<Posts> mPostsAdapter = new GridAdapter(inflater.getContext(), mPosts);
        GridView mGridView = (GridView) view.findViewById(R.id.grid_gv);

        mGridView.setAdapter(mPostsAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mGridItemListener = (GridItem) getActivity();
                mGridItemListener.onGridItemClicked(mPosts.get(position));

            }
        });


        return view;
    }

    public interface GridItem {
        public void onGridItemClicked(Posts post);
    }

}
