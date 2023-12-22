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

import java.util.ArrayList;

public class ListFrag extends Fragment {

    private static ListRecyclerAdapter adapter;
    RecyclerView recyclerView;
    View view;
    FloatingActionButton addBtn;

    public static ListRecyclerAdapter getAdapter(){
        return adapter;
    }
    public ListFrag() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);
        addBtn = view.findViewById(R.id.floating_add_btn);

        // Start Code for Add Records
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAcitvity = new Intent(getActivity().getApplicationContext(),SecondAcitivity.class);
                addAcitvity.addFlags(0);
                startActivity(addAcitvity);
            }
        });
        return view;
    }
}