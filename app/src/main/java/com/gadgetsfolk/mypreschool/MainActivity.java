package com.gadgetsfolk.mypreschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;

import com.gadgetsfolk.mypreschool.activity.CategoryActivity;
import com.gadgetsfolk.mypreschool.adapter.MainAdapter;
import com.gadgetsfolk.mypreschool.model.Main;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import in.completecourse.helper.HelperMethods;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Main> mCategories;

    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MainActivity";

    private FrameLayout adContainerView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_id));
        adContainerView.addView(adView);
        loadBanner();

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.interstitial_ad_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });

        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d("TAG", "The ad failed to show.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        mCategories = new ArrayList<>();
        mCategories.add(new Main(getString(R.string.title_numbers), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_zero, null)));
        mCategories.add(new Main(getString(R.string.title_alpha), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_a, null)));
        mCategories.add(new Main(getString(R.string.title_colors), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_ellipse, null)));
        mCategories.add(new Main(getString(R.string.title_animals), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_turtle, null)));
        mCategories.add(new Main(getString(R.string.title_insects), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_earthworm, null)));
        mCategories.add(new Main(getString(R.string.title_fruits), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_apple, null)));
        mCategories.add(new Main(getString(R.string.title_vegetables), ResourcesCompat.getDrawable(getResources(), R.drawable.ic_veg, null)));

        MainAdapter mainAdapter = new MainAdapter(mCategories, this);
        recyclerView.setAdapter(mainAdapter);

        recyclerView.addOnItemTouchListener(new HelperMethods.RecyclerTouchListener(this, position -> {
            if (mInterstitialAd != null) mInterstitialAd.show(MainActivity.this);
            else Log.d(TAG, "The interstitial ad wasn't ready yet.");
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            switch (position){
                case 0:
                    intent.putExtra("type", "numbers");
                    startActivity(intent);
                    break;
                case 1:
                    intent.putExtra("type", "alphabets");
                    startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("type", "colors");
                    startActivity(intent);
                    break;
                case 3:
                    intent.putExtra("type", "animals");
                    startActivity(intent);
                    break;
                case 4:
                    intent.putExtra("type", "insects");
                    startActivity(intent);
                    break;
                case 5:
                    intent.putExtra("type", "fruits");
                    startActivity(intent);
                    break;
                case 6:
                    intent.putExtra("type", "vegetables");
                    startActivity(intent);
                    break;
            }
        }));

        //if (BuildConfig.DEBUG) MediationTestSuite.launch(this);

    }

    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);


        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

}