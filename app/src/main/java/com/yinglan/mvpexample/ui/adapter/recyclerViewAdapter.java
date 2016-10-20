package com.yinglan.mvpexample.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yinglan.mvpexample.R;

import java.util.List;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> images;
    private Context context;

    public RecyclerViewAdapter(Context context, List<String> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list, null);
        ImageViewHolder ivh = new ImageViewHolder(layoutView);
        return ivh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
        String imageFuli = images.get(position);
        Glide.with(context)
                .load(imageFuli)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewHolder.imageView);
        ViewCompat.setTransitionName(imageViewHolder.imageView, imageFuli);


    }

    @Override
    public int getItemCount() {
        return this.images.size();
    }

    protected abstract void onItemClick(View v, int position);

    public String getImage(int position) {
        return images.get(position);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}

