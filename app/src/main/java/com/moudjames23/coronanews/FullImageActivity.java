package com.moudjames23.coronanews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.moudjames23.coronanews.app.Constant;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        ImageView imageView = (ImageView) findViewById(R.id.imgFull);


        String url = getIntent().getStringExtra("URL");



        Picasso.with(this)
                .load(Constant.IMAGE_URL+url)
                .fit().centerInside()
                .into(imageView);

        //imageView.setImageResource(R.drawable.property);



    }

    private void animateView(TextView view)
    {
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);

        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f

        );
        animation.setDuration(500);
        set.addAnimation(animation);

        view.startAnimation(set);
        view.setVisibility(View.VISIBLE);
    }

    public void close(View view) {
        onBackPressed();
    }
}
