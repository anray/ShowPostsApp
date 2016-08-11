package com.softteq.testappsoftteq.network.restmodels;

import com.softteq.testappsoftteq.network.response.Posts;
import com.softteq.testappsoftteq.network.response.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anray on 08.08.2016.
 */
public interface RestService {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("users/{userId}")
    Call<Users> getUser(@Path("userId") String userId);
}
