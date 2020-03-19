package com.moudjames23.coronanews.model;

import java.io.Serializable;

/**
 * Created by admin on 17/03/2020.
 */
public class Video implements Serializable{

    private String titre;
    private String url;

    public Video(String titre, String url) {
        this.titre = titre;
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public String getUrl() {
        return url;
    }
}
