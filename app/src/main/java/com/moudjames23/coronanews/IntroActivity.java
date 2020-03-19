package com.moudjames23.coronanews;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Informer", "Obtenez toutes les informations en temps réel sur le covid-19 (Coronavirus)", R.drawable.banniere);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Sensibiliser", "Apprenez les bonnes pratiques pour sauver des vies et la vôtre.", R.drawable.banniere);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Source", "Toutes les informations sur cette application proviennent de https://www.worldometers.info/.", R.drawable.banniere);

        ahoyOnboarderCard1.setBackgroundColor(R.color.white);
        ahoyOnboarderCard2.setBackgroundColor(R.color.white);
        ahoyOnboarderCard3.setBackgroundColor(R.color.white);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.grey_600);
        }

        setFinishButtonTitle("Passer");
        showNavigationControls(false);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.solid_one);
        colorList.add(R.color.solid_two);
        colorList.add(R.color.solid_three);

        setColorBackground(colorList);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/NunitoSans-Regular.ttf");
        setFont(face);

        setOnboardPages(pages);


    }

    @Override
    public void onFinishButtonPressed() {
        finish();
        startActivity(new Intent(this, PhoneNumberActivity.class));
    }

}
