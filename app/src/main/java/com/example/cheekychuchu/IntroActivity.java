package com.example.cheekychuchu;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle("Welcome to Cheeky Chuchu");
        sliderPage.setImageDrawable(R.drawable.paw);
        sliderPage.setDescription("This game will attempt to catch the cutest living being on the planet Earth");
        sliderPage.setBgColor(Color.parseColor("#E16E3C"));
        addSlide(AppIntroFragment.newInstance(sliderPage));
        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
        //setFlowAnimation();
        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);
        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Rules");
        sliderPage1.setImageDrawable(R.drawable.dog);
        sliderPage1.setDescription("You will be able to catch chuchu when you are within chuchu's proximity of 50 meters" + "\n" + "You have 10 minutes to do so" + "\n" + "Best of luck!" + "\n" + "Chuchu awaits your presence around him");
        sliderPage1.setBgColor(Color.parseColor("#E16E3C"));
        addSlide(AppIntroFragment.newInstance(sliderPage1));
        askForPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 2);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}