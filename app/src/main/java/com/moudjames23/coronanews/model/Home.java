package com.moudjames23.coronanews.model;

import java.io.Serializable;
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
        return videos;
    }

    public Stats getLocal() {
        return local;
    }

    public List<Galerie> getGaleries() {
        return galeries;
    }

    public Stats getGlobal() {
        return global;
    }

    public List<Stats> getStats() {
        return stats;
    }
}
