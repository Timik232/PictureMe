package com.example.coursework;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursework.Database.DataChatPerson;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public String TAG = "RecyclerAdapter";
        final TextView fio;
        final TextView text;
        final TextView date;
        final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fio = itemView.findViewById(R.id.fio);
            this.text = itemView.findViewById(R.id.person_text);
            this.date = itemView.findViewById(R.id.person_time);
            this.imageView = itemView.findViewById(R.id.ava);
        }
    }
    private final LayoutInflater inflater;
    private List<DataChatPerson> items;
    private NavController navController;
    public PersonAdapter(Context context, NavController navController) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.navController = navController;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataChatPerson item = items.get(position);
        String inputText = item.getLast_message();
        if (inputText.length() > 25) {
            inputText = inputText.substring(0, 20) + "...";
        }
        holder.text.setText(inputText);
        holder.fio.setText(item.getName());
        holder.date.setText(item.getLast_date());
        holder.imageView.setImageResource(item.getPhoto());
        Glide.with(holder.imageView.getContext()).load(item.getPhoto()).circleCrop().into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("person", holder.fio.getText().toString());
                navController.navigate(R.id.one_chat, bundle);
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setChatPersons(List<DataChatPerson> chatPersons){
        this.items.clear();
        this.items.addAll(chatPersons);
        Log.d("WOORK", "It's OK");
        notifyDataSetChanged();
    }
}

