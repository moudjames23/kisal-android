package com.moudjames23.coronanews.model;

import java.io.Serializable;

/**
 * Created by admin on 18/03/2020.
 */
public class Galerie  implements Serializable{

    private String image;

    public Galerie(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
