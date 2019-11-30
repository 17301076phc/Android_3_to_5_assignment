package com.example.firstassignment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private  List<Course> mcourseList;
    Context context;
    private OnItemClickListener mClickListener;

    public CourseAdapter(Context context,List<Course> c){
        this.context=context;
        mcourseList=c;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView courseImage;
        TextView courseName;
        private OnItemClickListener mListener;
        public ViewHolder (View view,OnItemClickListener listener)
        {
            super(view);
            mListener = listener;
            view.setOnClickListener(this);
            courseImage = (ImageView) view.findViewById(R.id.course_image);
            courseName = (TextView) view.findViewById(R.id.course_name);
        }
        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.course_item, null);;
        ViewHolder holder = new ViewHolder(view,mClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course c = mcourseList.get(position);
        holder.courseImage.setImageResource(c.getImageid());
        holder.courseName.setText(c.getName());
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "detail message of course", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mcourseList.size();
    }

}
