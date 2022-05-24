package com.alvaroh12.bubble;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DynamicRVAdapter extends  RecyclerView.Adapter<DynamicRVAdapter.DynamicRVViewHolder> implements View.OnClickListener{

    ArrayList<DynamicRVModel> items;
    private View.OnClickListener listener;

    public DynamicRVAdapter(ArrayList<DynamicRVModel> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public DynamicRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv, parent, false);
        view.setOnClickListener(this);
        DynamicRVViewHolder dynamicRVModel = new DynamicRVViewHolder(view);

        return dynamicRVModel;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVViewHolder holder, int position) {

        DynamicRVModel currentItem = items.get(position);
        holder.textView.setText(currentItem.getName());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }


    public static class DynamicRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        //ImageView imageView;
        ConstraintLayout constraintLayout;

        public DynamicRVViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.tipoOferta);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);


        }


    }
}
