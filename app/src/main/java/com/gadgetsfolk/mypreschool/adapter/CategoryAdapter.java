package com.gadgetsfolk.mypreschool.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gadgetsfolk.mypreschool.R;
import com.gadgetsfolk.mypreschool.model.Category;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categories;
    private Activity activity;
    private String type;
    private MediaPlayer mp = new MediaPlayer();
    private boolean isPLAYING = false;

    public CategoryAdapter(ArrayList<Category> categories, Activity activity, String type) {
        this.categories = categories;
        this.activity  = activity;
        this.type = type;
    }

    @NonNull
    @NotNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.ViewHolder holder, int position) {
        Category item = categories.get(position);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSub_title());

        if (item.getSub_icon() != null)
            GlideToVectorYou.init().with(activity).load(Uri.parse(item.getSub_icon()), holder.subIcon);

        GlideToVectorYou.init().with(activity).load(Uri.parse(item.getIcon_url()), holder.icon);

        if (!type.equals("colors")){
            if (position % 5 == 0)
                holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.color1, null));
            else if (position % 5 == 1)
                holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.color2, null));
            else if (position % 5 == 2)
                holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.color3, null));
            else if (position % 5 == 3)
                holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.color4, null));
            else if (position % 5 == 4)
                holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.color5, null));
        }else{
            holder.title.setTextColor(ResourcesCompat.getColor(activity.getResources(), R.color.black, null));
            holder.cardView.setCardBackgroundColor(ResourcesCompat.getColor(activity.getResources(), R.color.burlyWood, null));
        }

        holder.icon.setOnClickListener(v -> {
            ObjectAnimator iconAnimator = ObjectAnimator.ofFloat(holder.icon, View.ROTATION_Y, 0f, 400f);
            iconAnimator.setDuration(1000);
            //iconAnimator.setInterpolator(new BounceInterpolator());
            iconAnimator.start();
            ObjectAnimator titleAnimator = ObjectAnimator.ofFloat(holder.title, View.ROTATION_Y, 400f, 0f);
            titleAnimator.setDuration(1000);
            //titleAnimator.setInterpolator(new BounceInterpolator());
            titleAnimator.start();

            if (!isPLAYING) {
                isPLAYING = true;
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(item.getSound_url());
                    mp.prepare();
                    mp.start();
                }
                catch (IOException e) {
                    Log.e("LOG_TAG", e.getMessage());
                }
            }else {
                isPLAYING = false;
                stopPlaying();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;
        private TextView subTitle;
        private MaterialCardView cardView;
        //private ProgressBar progressBar;
        private ImageView subIcon;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.tv_title);
            subTitle = itemView.findViewById(R.id.tv_sub_title);
            cardView = itemView.findViewById(R.id.card);
            //progressBar = itemView.findViewById(R.id.progress);
            subIcon = itemView.findViewById(R.id.sub_icon);
        }
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

}
