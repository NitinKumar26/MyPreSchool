package com.gadgetsfolk.mypreschool.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gadgetsfolk.mypreschool.R;
import com.gadgetsfolk.mypreschool.adapter.CategoryAdapter;
import com.gadgetsfolk.mypreschool.model.Category;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<Category> categories;
    private String type;
    private CategoryAdapter adapter;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    private FrameLayout adContainerView;
    private AdView adView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        type = getIntent().getStringExtra(getString(R.string.type));

        if (type != null) Log.e(getString(R.string.type), type);

        progressBar = findViewById(R.id.progress_horizontal);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(type.toUpperCase());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(ResourcesCompat.getDrawable(this.getResources(), R.drawable.ic_baseline_arrow_back_24, null));
        }

        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_id));
        adContainerView.addView(adView);
        loadBanner();

        categories = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter = new CategoryAdapter(categories, this, type);
        recyclerView.setAdapter(adapter);
        if (type != null)  getCategories(type);

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

    private void getNumbers() {
        FirebaseFirestore.getInstance().collection(getString(R.string.numbers)).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
                    categories.addAll(list);
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e ->
                Toast.makeText(CategoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));

    }

    private void getAlphabets() {
        FirebaseFirestore.getInstance().collection(getString(R.string.alphabets)).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
                    categories.addAll(list);
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getColors() {
        FirebaseFirestore.getInstance().collection(getString(R.string.colors))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getAnimals() {
        FirebaseFirestore.getInstance().collection(getString(R.string.animals))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getInsects() {
        FirebaseFirestore.getInstance().collection(getString(R.string.insects))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getPlants() {
        FirebaseFirestore.getInstance().collection(getString(R.string.plants))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getFruits() {
        FirebaseFirestore.getInstance().collection(getString(R.string.fruits))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getVegetables() {
        FirebaseFirestore.getInstance().collection(getString(R.string.vegetables))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getShapes(){
        FirebaseFirestore.getInstance().collection(getString(R.string.shapes)).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
                    categories.addAll(list);
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e ->
                Toast.makeText(CategoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getCategories(String type){
        switch (type){
            case "numbers":
                getNumbers();
                break;
            case "alphabets":
                getAlphabets();
                break;
            case "colors":
                getColors();
                break;
            case "animals":
                getAnimals();
                break;
            case "insects":
                getInsects();
                break;
            case "plants":
                getPlants();
                break;
            case "fruits":
                getFruits();
                break;
            case "vegetables":
                getVegetables();
                break;
            case "shapes":
                getShapes();
                break;
        }
    }
}
