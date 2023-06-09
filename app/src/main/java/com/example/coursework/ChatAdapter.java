package com.example.coursework;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public String TAG = "RecyclerAdapter";
        final TextView time;
        final TextView text;
        final ConstraintLayout back;
        final LinearLayout wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.person_text);
            this.time = itemView.findViewById(R.id.person_time);
            this.back = itemView.findViewById(R.id.back);
            this.wrapper = itemView.findViewById(R.id.message_wrapper);
        }
    }
    private final LayoutInflater inflater;
    private List<Message> items;
    private String photographName;
    public ChatAdapter(Context context, String name) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.photographName = name;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message item = items.get(position);
        holder.text.setText(item.getText());
        holder.time.setText(item.getTime());
        if (!Objects.equals(item.getName(), photographName)){
            Log.d("InAdapter","not equal");
            //int color = Color.rgb(85,27,250);
            Drawable border = ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.user_message_border);
            holder.back.setBackground(border);
            holder.text.setTextColor(Color.WHITE);
            holder.time.setTextColor(Color.WHITE);
            holder.wrapper.setGravity(Gravity.END);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setChatMessages(List<Message> Messages){
        this.items.clear();
        this.items.addAll(Messages);
        notifyDataSetChanged();
    }
}
