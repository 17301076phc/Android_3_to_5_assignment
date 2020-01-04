package com.example.firstassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourselistAdapter extends BaseExpandableListAdapter {
    private ArrayList<Coursegroup> gData;
    private ArrayList<ArrayList<CourseIndro>> iData;
    private Context mContext;

    public CourselistAdapter(ArrayList<Coursegroup> gData,ArrayList<ArrayList<CourseIndro>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }
    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_group, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(gData.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.img_icon=(ImageView)convertView.findViewById(R.id.course_image);
            childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.course_name);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getImageid());
        childViewHolder.tvTitle.setText(iData.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    private static class GroupViewHolder {
        TextView tvTitle;
    }

    private static class ChildViewHolder {
        ImageView img_icon;
        TextView tvTitle;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
