package com.example.coursework;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Repository.Chat_Person;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(imageView.getContext(), fio.getText(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, fio.getText().toString());
                }
            });
            this.fio = itemView.findViewById(R.id.fio);
            this.text = itemView.findViewById(R.id.person_text);
            this.date = itemView.findViewById(R.id.person_time);
            this.imageView = itemView.findViewById(R.id.ava);
        }
    }

    private final LayoutInflater inflater;
    private List<DataChatPerson> items;
    public PersonAdapter(Context context, List<DataChatPerson> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
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
        holder.text.setText(item.getLast_message());
        holder.fio.setText(item.getName());
        holder.date.setText(item.getLast_date());
        holder.imageView.setImageResource(item.getPhoto());
        Glide.with(holder.imageView.getContext()).load(item.getPhoto()).circleCrop().into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setChatPersons(List<DataChatPerson> chatPersons){
        this.items = chatPersons;
        notifyDataSetChanged();
    }
}

