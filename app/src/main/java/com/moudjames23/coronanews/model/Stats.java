package com.moudjames23.coronanews.model;

import com.moudjames23.coronanews.app.Helper;

/**
 * Created by admin on 17/03/2020.
 */
public class Stats {

    private String countryregion;
    private int cases;
    private int confirmed;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private int active;
    private int critical;

    public Stats() {
    }

    public Stats(String countryregion, int confirmed, int todayCases, int deaths, int recovered) {
        this.countryregion = countryregion;
        this.confirmed = confirmed;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String message()
    {
        return "Infectés: " + Helper.formatPrice("" +this.getConfirmed())+ " | Guéris: " +Helper.formatPrice("" +this.getRecovered())+ " | Décédés: " +Helper.formatPrice("" +this.getDeaths());
    }

    public int getCases() {
        return cases;
    }

    public String getCountryregion() {
        return countryregion;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    @Override
    public String toString() {
        return "Countrie{" +
                "country='" + countryregion + '\'' +
                ", cases=" + confirmed +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", critical=" + critical +
                '}';
    }
}
