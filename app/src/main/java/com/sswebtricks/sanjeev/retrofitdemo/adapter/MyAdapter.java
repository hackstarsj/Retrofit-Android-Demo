package com.sswebtricks.sanjeev.retrofitdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sswebtricks.sanjeev.retrofitdemo.R;
import com.sswebtricks.sanjeev.retrofitdemo.model.ItemModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjeev on 18/1/18.
 */


public class MyAdapter extends BaseAdapter {
    private List<ItemModal> mItems = new ArrayList<ItemModal>();
    private final LayoutInflater mInflater;


    public MyAdapter(Context context, List<ItemModal> items) {
        mInflater = LayoutInflater.from(context);
        mItems=items;

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ItemModal getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        TextView title;
        TextView body;
        ImageView imageView;
        CardView cardView;

        if (v == null) {
            v = mInflater.inflate(R.layout.griditem, viewGroup, false);
            v.setTag(R.id.title, v.findViewById(R.id.title));
            v.setTag(R.id.body, v.findViewById(R.id.body));
            v.setTag(R.id.cards,v.findViewById(R.id.cards));
            v.setTag(R.id.img,v.findViewById(R.id.img));
        }

        title = (TextView) v.getTag(R.id.title);
        body = (TextView) v.getTag(R.id.body);
        cardView=(CardView)v.getTag(R.id.cards);
        imageView=(ImageView)v.getTag(R.id.img);
        ItemModal item = getItem(i);


        title.setText(item.title);
        body.setText(item.body);
        imageView.setImageResource(R.drawable.nature);

        cardView.setCardBackgroundColor(item.colors);

        return v;
    }


    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }




}