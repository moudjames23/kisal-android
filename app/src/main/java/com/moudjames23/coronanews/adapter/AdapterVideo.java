package com.moudjames23.coronanews.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.VideoPlay;
import com.moudjames23.coronanews.model.Video;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by admin on 24/08/2019.
 */
public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.VHVideo> {

    private List<Video> data;

    private OnVideoClickListener listener;

    public AdapterVideo(List<Video> data) {
        this.data = data;
    }

    @Override
    public VHVideo onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHVideo(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(VHVideo holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnVideoClickListener(OnVideoClickListener onVideoClickListener) {
        this.listener = onVideoClickListener;
    }

    public class VHVideo extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tvTitre;

        public VHVideo(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_video);
            tvTitre = itemView.findViewById(R.id.tv_video_title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onVideoClick(data.get(getLayoutPosition()));
                }
            });
        }

        public void bind(Video video) {
            String URL_THUMBNAIL = "http://img.youtube.com/vi/" + VideoPlay.getYoutubeVideoId(video.getUrl()) + "/0.jpg";

            Picasso.with(itemView.getContext())
                    .load(URL_THUMBNAIL)
                    .fit().centerCrop()
                    .into(img);

            tvTitre.setText(video.getTitre());



        }


    }

    public interface OnVideoClickListener {
        void onVideoClick(Video video);
    }
}
