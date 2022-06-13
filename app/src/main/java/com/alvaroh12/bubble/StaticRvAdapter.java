package com.alvaroh12.bubble;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaroh12.bubble.Interface.UpdateRecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> implements View.OnClickListener{

    private ArrayList<StaticRvModel> items;// Puedo cambiar el <> por <Usuario>
    private View.OnClickListener listener;



    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        view.setOnClickListener(this);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);

        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder,int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getTxt());


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

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linearLayour);


        }


    }

}
