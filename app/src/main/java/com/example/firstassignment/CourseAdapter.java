package com.example.firstassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_FIRST=0;
    private static final int TYPE_SECOND=1;
    private static final int TYPE_THIRD=2;

    private List<CourseIndro> coursedata;
    private List<Images> imagedata;
    private OnItemClickListener mClickListener;

    public CourseAdapter(List<CourseIndro> c, List<Images> cimage){
        coursedata=c;
        imagedata=cimage;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_FIRST:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.type_first,parent,false);
                return new BannerViewHolder(view);
            case TYPE_SECOND:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.type_second,parent,false);
                return new CourseList(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BannerViewHolder){
            List<Integer> im=new ArrayList<>();
            for (int i=0;i<imagedata.size();i++){
                im.add(imagedata.get(i).getPic());
            }

            Banner banner=((BannerViewHolder)holder).banner;
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setImageLoader(new BannerGlideImageLoader());
            banner.setBannerAnimation(Transformer.ZoomOutSlide);
            banner.setDelayTime(5000);
            banner.isAutoPlay(true);
            banner.setIndicatorGravity(BannerConfig.CENTER);

            banner.setImages(im).setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    switch (position){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                }
            }).start();
        }
        else if(holder instanceof CourseList) {
            TextView tv = ((CourseList) holder).tv;
            ImageView iv = ((CourseList) holder).iv;
            TextView sms=((CourseList)holder).sms;

            tv.setText(coursedata.get(position).getName());
            iv.setImageResource(coursedata.get(position).getImageid());
            if (mClickListener != null) {
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("请输入SMS要分享的电话：");
                    builder.setIcon(R.drawable.smsdialog);
                    EditText editText=new EditText(v.getContext());
                    builder.setView(editText);
                    builder.setPositiveButton("分享", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mobile = editText.getText().toString();
                            String message=tv.getText().toString();
                            if (mobile!=null){
                                SmsManager smsManager=SmsManager.getDefault();
                                List<String> texts = smsManager.divideMessage(message);

                                for(String text : texts) {
                                    smsManager.sendTextMessage(mobile, null, text, null, null);
                                }
                            }
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position){
       if (position==0) {
           return TYPE_FIRST;
       }
       else {
            return TYPE_SECOND;
       }
    }

    @Override
    public int getItemCount() {
        return coursedata==null?0:coursedata.size();
    }


}
