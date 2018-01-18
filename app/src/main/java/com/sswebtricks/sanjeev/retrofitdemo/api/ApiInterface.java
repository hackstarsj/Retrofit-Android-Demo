package com.sswebtricks.sanjeev.retrofitdemo.api;

/**
 * Created by sanjeev on 18/1/18.
 */

import com.sswebtricks.sanjeev.retrofitdemo.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("posts")
    Call<List<PostModel>> getPost();

}