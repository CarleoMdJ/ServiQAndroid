package com.serviq.serviq;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by gildardo on 8/12/16.
 */

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.MyViewHolder> {
    private Context mContext;
    private List<Comida> comidaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView comida;
        public ImageView avatar, like, fast;


        public MyViewHolder(View view) {
            super(view);
            comida = (TextView) view.findViewById(R.id.titleTextView);
            avatar = (ImageView) view.findViewById(R.id.coverImageView);
            like = (ImageView) view.findViewById(R.id.likeImageView);
            fast = (ImageView) view.findViewById(R.id.shareImageView);

        }
    }

    public ComidaAdapter(Context mContext, List<Comida> comidaList) {
        this.mContext = mContext;
        this.comidaList = comidaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Comida comida = comidaList.get(position);
        holder.comida.setText(comida.getComida());

        Glide.with(mContext).load(comida.getThumbnail()).into(holder.avatar);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Agregado a favoritos", Toast.LENGTH_LONG).show();
            }
        });
        holder.fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Carrito rapido", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return comidaList.size();
    }
}
