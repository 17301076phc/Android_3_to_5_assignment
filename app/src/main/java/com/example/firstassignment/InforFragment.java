package com.example.firstassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class InforFragment extends Fragment {

    private Button btmath,btcomputer,btenglish,bteconomy,btmmind,btlaw,btyasi,btkaoyan,btcet;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.imfor_fragment,container,false);
        btmath=(Button)contentView.findViewById(R.id.btnmath);
        btcomputer=(Button)contentView.findViewById(R.id.btncomputer);

        btmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contentView.getContext(),"你点击了数学",Toast.LENGTH_SHORT).show();
            }
        });
        return contentView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
