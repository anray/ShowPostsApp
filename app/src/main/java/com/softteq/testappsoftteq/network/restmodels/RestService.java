package com.softteq.testappsoftteq.network.restmodels;

import com.softteq.testappsoftteq.network.response.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by anray on 08.08.2016.
 */
public interface RestService {

    @GET("posts")
    Call<List<Posts>> getPosts();
}
