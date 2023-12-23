package com.chandan.triplist;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GirlsFrag extends Fragment {
    private View view;
    private static GirlRecyclerViewAdapter adapter;
    private static RecyclerView recyclerView;
    private FloatingActionButton addBtn;
    public GirlsFrag() {
        // Required empty public constructor
    }

    public static GirlRecyclerViewAdapter getAdapter(){ return adapter;}
    public static RecyclerView getRecyclerView(){ return recyclerView;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_girls, container, false);
        recyclerView = view.findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GirlRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        addBtn = view.findViewById(R.id.floating_add_btn);

        // code for add records
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAcitvity = new Intent(getActivity().getApplicationContext(),SecondAcitivity.class);
                addAcitvity.addFlags(0);
                addAcitvity.putExtra("position",2);
                startActivity(addAcitvity);
            }
        });
        return view;
    }
}