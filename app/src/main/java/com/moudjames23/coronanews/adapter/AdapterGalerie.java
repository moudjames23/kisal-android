package com.moudjames23.coronanews.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moudjames23.coronanews.FullImageActivity;
import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.app.Constant;
import com.moudjames23.coronanews.model.Galerie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by admin on 18/03/2020.
 */
public class AdapterGalerie extends RecyclerView.Adapter<AdapterGalerie.VHGalerie>{

    private List<Galerie> data;

    private OnImageClickListener listener;

    public AdapterGalerie(List<Galerie> data) {
        this.data = data;
    }

    @Override
    public VHGalerie onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHGalerie(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_galerie, parent, false));
    }

    @Override
    public void onBindViewHolder(VHGalerie holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnImageeClickListener(OnImageClickListener onImageeClickListener)
    {
        this.listener = onImageeClickListener;
    }

    public class VHGalerie extends RecyclerView.ViewHolder
    {
        private ImageView imageView;

        public VHGalerie(final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_galerie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), FullImageActivity.class);
                    //intent.setAction(Constant.ACTION_FROM_LOCAL);
                    intent.putExtra("URL", data.get(getLayoutPosition()).getImage());
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) itemView.getContext(),
                                    imageView,
                                    ViewCompat.getTransitionName(imageView));
                    itemView.getContext().startActivity(intent, options.toBundle());
                }
            });
        }

        public void bind(Galerie galerie)
        {
            String url = Constant.IMAGE_URL+ galerie.getImage();
            Log.d("main_tag", "bind: " +url);

            Picasso.with(itemView.getContext())
                    .load(url)
                    .fit().centerCrop()
                    .into(imageView);
        }
    }

    public interface OnImageClickListener
    {
        void onImageClick(Galerie galerie, int position);
    }
}
