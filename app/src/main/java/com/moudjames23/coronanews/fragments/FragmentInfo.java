package com.moudjames23.coronanews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.moudjames23.coronanews.MainActivity;
import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.adapter.AdapterStats;
import com.moudjames23.coronanews.app.Helper;
import com.moudjames23.coronanews.customs.FontTextView;
import com.moudjames23.coronanews.model.Stats;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 17/03/2020.
 */
public class FragmentInfo extends Fragment {

    public static String TAG = "fragment_info";

    @Bind(R.id.tv_count_infecter)
    FontTextView tvInfecter;

    @Bind(R.id.tv_count_dead)
    FontTextView tvdead;

    @Bind(R.id.tv_count_gueris)
    FontTextView tvGueris;

    @Bind(R.id.rv_stats)
    RecyclerView rvStats;

    @Bind(R.id.rl_info)
    RelativeLayout rlInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_info, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    public void init(Stats stats, List<Stats> data, MainActivity mainActivity)
    {
        tvInfecter.setText(Helper.formatPrice(""+stats.getCases()));
        tvGueris.setText(Helper.formatPrice(""+stats.getRecovered()));
        tvdead.setText(Helper.formatPrice(""+stats.getDeaths()));

        setupStats(data, mainActivity);
    }

    public void setupStats(List<Stats> data, final MainActivity mainActivity)
    {
        rvStats.setHasFixedSize(true);
        rvStats.setNestedScrollingEnabled(false);
        rvStats.setLayoutManager(new LinearLayoutManager(getActivity()));

        AdapterStats adapter = new AdapterStats(data);

        rvStats.setAdapter(adapter);

        rvStats.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    mainActivity.bottomNavigationBar.hide(true);

                } else {
                    mainActivity.bottomNavigationBar.show(true);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
