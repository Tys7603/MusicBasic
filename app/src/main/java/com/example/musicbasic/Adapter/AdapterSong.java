package com.example.musicbasic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicbasic.DetailActivity;
import com.example.musicbasic.Interface.ItemClickUri;
import com.example.musicbasic.Modal.Song;
import com.example.musicbasic.R;

import java.util.List;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.viewHolder>  {
    List<Song> list;
    Context context;

    ItemClickUri itemClickUri;

    public AdapterSong(List<Song> list, Context context ,  ItemClickUri itemClickUri) {
        this.list = list;
        this.context = context;
        this.itemClickUri = itemClickUri;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (list != null){
            holder.nameSong.setText(list.get(position).getNameSong());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickUri.onClickItemUri(list.get(holder.getAdapterPosition()).getFile());
                    context.startActivity(new Intent(context, DetailActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView nameSong;
        LinearLayout linearLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nameSong = itemView.findViewById(R.id.name_song);
            linearLayout = itemView.findViewById(R.id.click_layout);
        }
    }
}
