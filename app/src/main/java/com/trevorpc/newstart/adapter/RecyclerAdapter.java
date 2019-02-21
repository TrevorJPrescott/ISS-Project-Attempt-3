package com.trevorpc.newstart.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trevorpc.newstart.R;
import com.trevorpc.newstart.databinding.ItemLayoutBinding;
import com.trevorpc.newstart.model.model.object.Response;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>  {

    private Context context;
    private List<Response> responses;

    public RecyclerAdapter(Context context, List<Response> responses) {
        this.context = context;
        this.responses = responses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_layout,
                viewGroup,
                false);
        MyViewHolder holder = new MyViewHolder(itemLayoutBinding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Response response = responses.get(i);
        myViewHolder.itemLayoutBinding.setResponse(response);
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public void updateList(List<Response> responses) {
        this.responses.addAll(responses);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemLayoutBinding;

        public MyViewHolder(@NonNull ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }

}
