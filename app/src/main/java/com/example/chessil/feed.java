package com.example.chessil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class feed extends Fragment {

    private RecyclerView recyclerView1,recyclerView2;
    private TextView emptyView1,emptyView2;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feed,container,false);
        mAuth = FirebaseAuth.getInstance();
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView1);
        emptyView1 = (TextView) view.findViewById(R.id.empty_view);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        emptyView2 = (TextView) view.findViewById(R.id.empty_view2);


        //        if (dataset.isEmpty()) {
//            recyclerView.setVisibility(View.GONE);
//            emptyView.setVisibility(View.VISIBLE);
//        }
//        else {
        recyclerView1.setVisibility(View.GONE);
        emptyView1.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.GONE);
        emptyView2.setVisibility(View.VISIBLE);
//        }

        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}