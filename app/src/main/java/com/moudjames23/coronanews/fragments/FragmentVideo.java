package com.moudjames23.coronanews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moudjames23.coronanews.MainActivity;
import com.moudjames23.coronanews.R;
import com.moudjames23.coronanews.VideoPlay;
import com.moudjames23.coronanews.adapter.AdapterVideo;
import com.moudjames23.coronanews.model.Video;
import com.moudjames23.coronanews.networkapi.NetworkCallBack;
import com.moudjames23.coronanews.networkapi.WebService;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 17/03/2020.
 */
public class FragmentVideo extends Fragment implements AdapterVideo.OnVideoClickListener {

    public static String TAG = "fragment_video";

    @Bind(R.id.rv_videos)
    RecyclerView rvVideos;


    @Bind(R.id.loader)
    AVLoadingIndicatorView loader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_video, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loader.hide();


    }



    public void setupVideo(List<Video> data, final MainActivity mainActivity)
    {
        rvVideos.setHasFixedSize(true);
        rvVideos.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        AdapterVideo adapter = new AdapterVideo(data);
        adapter.setOnVideoClickListener(this);

        rvVideos.setAdapter(adapter);

        rvVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    public void onVideoClick(Video video) {

        Intent intent = new Intent(getActivity(), VideoPlay.class);
        intent.putExtra("DATA", video);

        startActivity(intent);

    }


}

