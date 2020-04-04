package com.moudjames23.coronanews.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18/03/2020.
 */
public class Home implements Serializable{

    private List<Video> videos;
    private List<Galerie> galeries;
    private Stats local;
    private Stats global;
    private List<Stats> stats;

    public List<Video> getVideos() {
        if (videos == null) {
            videos = new ArrayList<>();
        }
        return videos;
    }

    public Stats getLocal() {
        return local;
    }

    public List<Galerie> getGaleries() {
        //initialize only when required
        if (galeries == null) {
            galeries = new ArrayList<>();
        }
        return galeries;
    }

    public Stats getGlobal() {
        return global;
    }

    public List<Stats> getStats() {
        //initialize only when required
        if (stats == null ){
            stats = new ArrayList<>();
        }
        return stats;
    }
}
