package com.example.firstassignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseList extends RecyclerView.ViewHolder {
    public TextView tv,sms;
    public ImageView iv;
    public CourseList(@NonNull View itemView) {
        super(itemView);
        tv=itemView.findViewById(R.id.typesecond_text);
        iv=itemView.findViewById(R.id.typesecond_image);
        sms=itemView.findViewById(R.id.typesecond_sms);
    }
}
