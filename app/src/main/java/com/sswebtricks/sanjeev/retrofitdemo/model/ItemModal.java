package com.sswebtricks.sanjeev.retrofitdemo.model;

/**
 * Created by sanjeev on 18/1/18.
 */

public class ItemModal {
    public final String title;
    public final String body;
    public int colors;

    public ItemModal(String title, String body,int colors) {
        this.title = title;
        this.body= body;
        this.colors=colors;
    }

}