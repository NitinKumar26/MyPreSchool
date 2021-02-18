package com.gadgetsfolk.mypreschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.gadgetsfolk.mypreschool.activity.CategoryActivity;
import com.gadgetsfolk.mypreschool.adapter.MainAdapter;
import com.gadgetsfolk.mypreschool.model.Main;
import java.util.ArrayList;
import in.completecourse.helper.HelperMethods;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Main> mCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        mCategories = new ArrayList<>();
        mCategories.add(new Main("Numbers", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_zero, null)));
        mCategories.add(new Main("Alphabets", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_a, null)));
        mCategories.add(new Main("Colors", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_ellipse, null)));
        mCategories.add(new Main("Animals", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_turtle, null)));
        mCategories.add(new Main("Insects", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_earthworm, null)));
        mCategories.add(new Main("Plants", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cactus, null)));
        mCategories.add(new Main("Fruits", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_apple, null)));
        mCategories.add(new Main("Vegetables", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_veg, null)));

        MainAdapter mainAdapter = new MainAdapter(mCategories, this);
        recyclerView.setAdapter(mainAdapter);

        recyclerView.addOnItemTouchListener(new HelperMethods.RecyclerTouchListener(this, new HelperMethods.ClickListener() {
            @Override
            public void onClick(int position) {
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
                        intent.putExtra("type", "plants");
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("type", "fruits");
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("type", "vegetables");
                        startActivity(intent);
                        break;
                }
            }
        }));

    }
}