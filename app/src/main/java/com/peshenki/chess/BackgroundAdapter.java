package com.peshenki.chess;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<BackgroundItem> items;
    Callback callback;

    interface Callback {
        void onItemClicked(BackgroundItem item);
    }

    BackgroundAdapter(Context context, List<BackgroundItem> items, Callback callback) {
        this.items = items;
        this.callback = callback;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public BackgroundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.background_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BackgroundAdapter.ViewHolder holder, int position) {
        BackgroundItem background = items.get(position);
        holder.img.setImageResource(background.name);
        Log.e("HOLDER", Integer.valueOf(background.name).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ViewHolder(View view){
            super(view);
            img =  view.findViewById(R.id.imageView2);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClicked(items.get(getAdapterPosition()));
                    Log.e("Kek", "Clicked");
                }
            });
        }

    }
}
