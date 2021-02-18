package com.gadgetsfolk.mypreschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
//import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.gadgetsfolk.mypreschool.R;
//import com.gadgetsfolk.mypreschool.model.Book;
import com.gadgetsfolk.mypreschool.model.Main;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<Main> mBooksList;
    private Context mContext;

    public MainAdapter(ArrayList<Main> mBooksList, Context mContext) {
        this.mBooksList = mBooksList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Main item = mBooksList.get(position);

        holder.tvClass.setText(item.getName());

        holder.icon.setImageDrawable(item.getIcon());

        //holder.tvImage.setText(item.getName());
        /*
        if (position % 5 == 0) holder.rlBackGround.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.gradient_two, null));
        else if (position % 5 == 1) hdolder.rlBackGround.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.gradient_three, null));
        else if (position % 5 == 2) holder.rlBackGround.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.gradient_four, null));
        else if (position % 5 == 3) holder.rlBackGround.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.gradient_five, null));
        else holder.rlBackGround.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.gradient, null));

         */
    }

    @Override
    public int getItemCount() {
        return mBooksList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvClass;
        //private RelativeLayout rlBackGround;
        private ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClass = itemView.findViewById(R.id.tv_class);
            icon = itemView.findViewById(R.id.icon);
            //tvImage = itemView.findViewById(R.id.tv_img);
            //rlBackGround = itemView.findViewById(R.id.rel_back);
        }
    }
}
