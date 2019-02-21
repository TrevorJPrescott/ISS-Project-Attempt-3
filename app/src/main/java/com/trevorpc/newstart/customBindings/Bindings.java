package com.trevorpc.newstart.customBindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trevorpc.newstart.adapter.RecyclerAdapter;

import java.util.Date;

public class Bindings {
    @BindingAdapter("android:recycler")
    public static void loadImage(View view, RecyclerAdapter adapter) {

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @BindingAdapter("android:toDate")
    public static String toDate(View view, Integer riseTime) {
        Date date = new Date(Long.valueOf(riseTime)*1000);
        return date.toString();
    }
}
