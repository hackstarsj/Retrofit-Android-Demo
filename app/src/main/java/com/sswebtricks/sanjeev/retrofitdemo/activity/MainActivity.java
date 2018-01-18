package com.sswebtricks.sanjeev.retrofitdemo.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.GridView;

import com.sswebtricks.sanjeev.retrofitdemo.R;
import com.sswebtricks.sanjeev.retrofitdemo.adapter.MyAdapter;
import com.sswebtricks.sanjeev.retrofitdemo.api.ApiClient;
import com.sswebtricks.sanjeev.retrofitdemo.api.ApiInterface;
import com.sswebtricks.sanjeev.retrofitdemo.model.ItemModal;
import com.sswebtricks.sanjeev.retrofitdemo.model.PostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Response";
    int[] strings={Color.parseColor("#9c27b0"),Color.parseColor("#303f9f"),Color.parseColor("#388e3c"),Color.parseColor("#ff5722"),Color.parseColor("#009688"), Color.parseColor("#FF4081"),Color.parseColor("#f44336"),Color.parseColor("#2196f3"),Color.parseColor("#dd2c00"),Color.parseColor("#424242"),Color.parseColor("#37474f")};
    GridView gridView;
    MyAdapter myAdapter;
    List<ItemModal> mItems=new ArrayList<ItemModal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        gridView = (GridView)findViewById(R.id.gridview);
        myAdapter=new MyAdapter(getApplicationContext(),mItems);
        gridView.setAdapter(myAdapter);
        CallApi();

    }
    public void CallApi(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<PostModel>> call = apiService.getPost();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>>call, Response<List<PostModel>> response) {
                Log.d(TAG,response.body().get(0).getBody().toString());
                List<PostModel> list=response.body();
                for(int i=0;i<10;i++){

                    mItems.add(new ItemModal(list.get(i).getTitle(), list.get(i).getBody(),strings[(int)(Math.random()*10)]));

                }
                //MyAdapter myAdapter=new MyAdapter(getApplicationContext(),mItems);

               // gridView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PostModel>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
