package com.example.merve.issues.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.merve.issues.R;
import com.example.merve.issues.model.Person;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<Person> list_person;
    CustomItemClickListener listener;

    public HomeAdapter(List<Person> list_person, CustomItemClickListener listener) {

        this.list_person = list_person;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.person_name.setText(list_person.get(position).getName());
        holder.person_age.setText(list_person.get(position).getAge());
        holder.person_photo.setImageResource(list_person.get(position).getPhoto_id());

    }

    @Override
    public int getItemCount() {
        return list_person.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView person_name, person_age;
        private ImageView person_photo;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            card_view = view.findViewById(R.id.cardview_home);
            person_age = view.findViewById(R.id.person_age);
            person_name = view.findViewById(R.id.person_name);
            person_photo = view.findViewById(R.id.person_photo);
        }
    }


    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }
}
