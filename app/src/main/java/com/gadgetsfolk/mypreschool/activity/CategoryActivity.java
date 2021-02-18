package com.gadgetsfolk.mypreschool.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<Category> categories;
    private String type;
    private CategoryAdapter adapter;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        type = getIntent().getStringExtra("type");

        if (type != null) Log.e("type", type);

        progressBar = findViewById(R.id.progress_horizontal);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(type.toUpperCase());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(ResourcesCompat.getDrawable(this.getResources(), R.drawable.ic_baseline_arrow_back_24, null));
        }

        categories = new ArrayList<>();

        /*
        categories.add(new Category("Turtle","Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_turtle, null)));
        categories.add(new Category("Earthworm","Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_earthworm, null)));
        categories.add(new Category("Cactus", "Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cactus, null)));
        categories.add(new Category("Turtle", "Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_turtle, null)));
        categories.add(new Category("Earthworm", "Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_earthworm, null)));
        categories.add(new Category("Cactus", "Subtitle", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cactus, null)));
         */

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter = new CategoryAdapter(categories, this, type);
        recyclerView.setAdapter(adapter);
        if (type != null)  getCategories(type);

        /*
        recyclerView.addOnItemTouchListener(new HelperMethods.RecyclerTouchListener(this, new HelperMethods.ClickListener() {
            @Override
            public void onClick(int position) {
                //holder.progressBar.setVisibility(View.VISIBLE);

            }
        }));
         */
    }

    private void getNumbers() {
        FirebaseFirestore.getInstance().collection("numbers").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
                    categories.addAll(list);
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e ->
                Toast.makeText(CategoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));

    }

    private void getAlphabets() {
        FirebaseFirestore.getInstance().collection("alphabets").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
                    categories.addAll(list);
                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getColors() {
        FirebaseFirestore.getInstance().collection("colors")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getAnimals() {
        FirebaseFirestore.getInstance().collection("animals")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getInsects() {
        FirebaseFirestore.getInstance().collection("insects")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getPlants() {
        FirebaseFirestore.getInstance().collection("plants")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getFruits() {
        FirebaseFirestore.getInstance().collection("fruits")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getVegetables() {
        FirebaseFirestore.getInstance().collection("vegetables")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Category> list = queryDocumentSnapshots.toObjects(Category.class);
            categories.addAll(list);
            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {

        }).addOnCompleteListener(task -> progressBar.setVisibility(View.GONE));
    }

    private void getShapes(){
        FirebaseFirestore.getInstance().collection("shapes").get()
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
