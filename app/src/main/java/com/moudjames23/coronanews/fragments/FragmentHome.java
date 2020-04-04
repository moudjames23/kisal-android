package com.moudjames23.coronanews.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.moudjames23.coronanews.MainActivity;
import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.adapter.AdapterGalerie;
import com.moudjames23.coronanews.app.Constant;
import com.moudjames23.coronanews.customs.FontTextView;
import com.moudjames23.coronanews.model.Galerie;
import com.moudjames23.coronanews.model.Stats;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 17/03/2020.
 */
public class FragmentHome extends Fragment {

    public static String TAG = "fragment_home";

    @Bind(R.id.tv_count_infecter)
    FontTextView tvInfecter;


    @Bind(R.id.tv_count_dead)
    FontTextView tvdead;

    @Bind(R.id.tv_count_gueris)
    FontTextView tvGueris;

    @Bind(R.id.card_infectes)
    CardView cardInfecter;


    @Bind(R.id.card_dead)
    CardView cardDead;

    @Bind(R.id.card_gueri)
    CardView cardGueri;

    @Bind(R.id.rv_galeries)
    RecyclerView rvGalerie;

    private final int BUTTON_ANIM_DURATION = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_home, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void init(Stats info) {

        tvInfecter.setText(String.valueOf(info.getConfirmed()));
        tvdead.setText(String.valueOf(info.getDeaths()));
        tvGueris.setText(String.valueOf(info.getDeaths()));

        animation();
    }


    public void setupGalerie(List<Galerie> data, final MainActivity mainActivity) {
        Log.d(TAG, "setupGalerie: " + data.size());
        rvGalerie.setNestedScrollingEnabled(true);
        rvGalerie.setHasFixedSize(true);
        rvGalerie.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        AdapterGalerie adapter = new AdapterGalerie(data);


        rvGalerie.setAdapter(adapter);

        rvGalerie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mainActivity.bottomNavigationBar.hide(true);
                } else {
                    mainActivity.bottomNavigationBar.show(true);
                }

            }
        });

    }

    public Stats defaultCountryInfo(List<Stats> data) {
        for (Stats stat : data) {
            if (stat.getCountryregion().equals(Constant.DEFAULT_COUNTRY)) {
                return new Stats(stat.getCountryregion(), stat.getConfirmed(), stat.getTodayCases(), stat.getDeaths(), stat.getRecovered());
            }
        }
        return null;
    }

    private void animation() {
        cardInfecter.setTranslationX(-800);
        cardDead.setTranslationX(-800);
        cardGueri.setTranslationX(800);

        cardInfecter.setVisibility(View.VISIBLE);
        //cardToday.setVisibility(View.VISIBLE);
        cardDead.setVisibility(View.VISIBLE);
        cardGueri.setVisibility(View.VISIBLE);

        cardInfecter.animate()
                .translationX(0)
                .setDuration(BUTTON_ANIM_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setStartDelay(190)
                .start();


        cardDead.animate()
                .translationX(0)
                .setDuration(BUTTON_ANIM_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setStartDelay(190)
                .start();

        cardGueri.animate()
                .translationX(0)
                .setDuration(BUTTON_ANIM_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setStartDelay(190)
                .start();
    }


}
