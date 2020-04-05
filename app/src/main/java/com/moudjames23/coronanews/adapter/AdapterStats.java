package com.moudjames23.coronanews.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.app.Helper;
import com.moudjames23.coronanews.customs.FontTextView;
import com.moudjames23.coronanews.model.Stats;

import java.util.List;

/**
 * Created by admin on 18/03/2020.
 */
public class AdapterStats extends RecyclerView.Adapter<AdapterStats.VHStats>{

    private List<Stats> data;

    ColorGenerator generator = ColorGenerator.MATERIAL;

    public AdapterStats(List<Stats> data) {
        this.data = data;
    }

    @Override
    public VHStats onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHStats(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false));
    }

    @Override
    public void onBindViewHolder(VHStats holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VHStats extends RecyclerView.ViewHolder
    {

        private FontTextView tvCountry;
        private FontTextView tvStats;
        private ImageView img;

        public VHStats(View itemView) {
            super(itemView);

            tvCountry = itemView.findViewById(R.id.tv_countrie);
            tvStats = itemView.findViewById(R.id.tv_stats);
            img = itemView.findViewById(R.id.img_country);
        }

        public void bind(Stats stats) {
            tvCountry.setText(stats.getCountryregion());

            tvStats.setText(Helper.getStatsMessage(stats));

            TextDrawable drawable = TextDrawable.builder()
                    .buildRect(String.valueOf(stats.getCountryregion().charAt(0)), generator.getRandomColor());

            img.setImageDrawable(drawable);
        }
    }
}
