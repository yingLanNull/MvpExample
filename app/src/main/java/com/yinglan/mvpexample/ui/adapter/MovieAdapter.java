package com.yinglan.mvpexample.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.model.bean.MovieBean;

import java.util.List;

public abstract class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieBean.MovieInfo> images;
    private Context context;

    public MovieAdapter(Context context, List<MovieBean.MovieInfo> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
        MovieHolder ivh = new MovieHolder(layoutView);
        return ivh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MovieHolder imageViewHolder = (MovieHolder) holder;
        String imageFuli = images.get(position).iconaddress;
        Glide.with(context)
                .load(imageFuli)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewHolder.imageView);
        ViewCompat.setTransitionName(imageViewHolder.imageView, imageFuli);
        imageViewHolder.textView1.setText(images.get(position).tvTitle);
        imageViewHolder.textView2.setText(images.get(position).story.data.storyBrief);
    }

    @Override
    public int getItemCount() {
        return this.images.size();
    }

    protected abstract void onItemClick(View v, int position);

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;

        public MovieHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_img);
            textView1 = (TextView) itemView.findViewById(R.id.movie_title);
            textView2 = (TextView) itemView.findViewById(R.id.movie_storyBrief);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}

