package com.alvaroh12.bubble;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DynamicRVAdapter extends  RecyclerView.Adapter<DynamicRVAdapter.DynamicRVViewHolder> implements View.OnClickListener{

    List<DynamicRVModel> items;
    private View.OnClickListener listener;

    public DynamicRVAdapter(List<DynamicRVModel> items) {
        this.items = items;

    }

    public void setFilteredList(List<DynamicRVModel>dynamicRVModelList){
        this.items=dynamicRVModelList;
        notifyDataSetChanged();
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
        holder.textView.setText(currentItem.getTipo_oferta());
        holder.textView2.setText("Categoria: " + currentItem.getCategoria());
        holder.textView3.setText("Usuario: " + currentItem.getNombre());
        holder.textView4.setText("Precio: " + currentItem.getPrecio());

        if (currentItem.getCategoria().equalsIgnoreCase("arte y diseño")){
            holder.icon.setImageResource(R.drawable.arte);
        }else if(currentItem.getCategoria().equalsIgnoreCase("programación")){
            holder.icon.setImageResource(R.drawable.programacion);
        }else if(currentItem.getCategoria().equalsIgnoreCase("traducción")){
            holder.icon.setImageResource(R.drawable.traduccion);
        }else if(currentItem.getCategoria().equalsIgnoreCase("música")){
            holder.icon.setImageResource(R.drawable.musica);
        }else if(currentItem.getCategoria().equalsIgnoreCase("diseño web")){
            holder.icon.setImageResource(R.drawable.diseno_web);
        }else if(currentItem.getCategoria().equalsIgnoreCase("redes sociales")){
            holder.icon.setImageResource(R.drawable.redes_sociales);
        }


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
        TextView textView2;
        TextView textView3;
        TextView textView4;
        ImageView icon;
        ConstraintLayout constraintLayout;

        public DynamicRVViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.tipoOferta);
            textView2 = (TextView) itemView.findViewById(R.id.categoria);
            textView3 = (TextView) itemView.findViewById(R.id.usuarioOferta);
            textView4 = (TextView) itemView.findViewById(R.id.precio);
            icon = (ImageView) itemView.findViewById(R.id.imageIcon);
            constraintLayout = itemView.findViewById(R.id.constraintLayout2);


        }


    }
}
