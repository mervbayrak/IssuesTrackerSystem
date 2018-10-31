package com.example.merve.issues.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.merve.issues.R;
import com.example.merve.issues.adapter.HomeAdapter;
import com.example.merve.issues.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recycler_view;
    private List<Person> person_list;
    private View view;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initialiseEvents(view);
        return view;

    }

    private void initialiseEvents(View views) {
        recycler_view = view.findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        person_list = new ArrayList<Person>();

        person_list.add(new Person("Taha Kırca", "25 yaş", R.drawable.common_google_signin_btn_icon_dark_normal));
        person_list.add(new Person("Ayşe Fatma", "35 yaş", R.drawable.common_google_signin_btn_icon_dark));
        person_list.add(new Person("Ahmet Ali", "60 yaş", R.drawable.common_google_signin_btn_icon_dark));
        person_list.add(new Person("Pelin Pelin", "20 yaş", R.drawable.common_google_signin_btn_icon_dark));



        HomeAdapter adapter_items = new HomeAdapter(person_list, new HomeAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                Person person = person_list.get(position);

            }
        });
        recycler_view.setHasFixedSize(true);

        recycler_view.setAdapter(adapter_items);

        recycler_view.setItemAnimator(new DefaultItemAnimator());

    }


}
