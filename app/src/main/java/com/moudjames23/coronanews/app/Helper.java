package com.moudjames23.coronanews.app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.moudjames23.coronanews.model.Stats;

import java.math.BigInteger;
import java.text.DecimalFormat;


/**
 * Created by admin on 20/08/2019.
 */
public final class Helper {

    private Helper(){}

    public static void setupBackToolbar(Toolbar toolbar, final AppCompatActivity appCompatActivity)
    {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
            appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appCompatActivity.onBackPressed();
                }
            });
        }
    }

    public static String distanceFormat(double distance) {
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        return  decimalFormat.format(distance);
    }

    public static String formatPrice(String value) {
        BigInteger bigInteger = new BigInteger(value);
        DecimalFormat formatter = new DecimalFormat("#,###,###,###");

        return formatter.format(bigInteger);
    }


    public static String getStatsMessage(Stats stats) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(Constant.CONFIRMED);
        buffer.append(formatPrice(String.valueOf(stats.getConfirmed())));
        buffer.append(Constant.RECOVERED);
        buffer.append(formatPrice(String.valueOf(stats.getRecovered())));
        buffer.append(Constant.DEATH);
        buffer.append(formatPrice(String.valueOf(stats.getDeaths())));

        return buffer.toString();
    }

}
