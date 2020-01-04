package com.example.firstassignment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;

public class BannerViewHolder extends RecyclerView.ViewHolder {
    public Banner banner;
    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        banner=(Banner) itemView.findViewById(R.id.banner);
    }
}
