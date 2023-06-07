package com.example.coursework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.PortfolioPerson;

import java.util.ArrayList;
import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public String TAG = "PortfolioAdapter";
        final TextView fio;
        final TextView text;
        final TextView cost;
        final TextView rate;
        final ImageView icon;
        final ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.fio = itemView.findViewById(R.id.fio);
            this.text = itemView.findViewById(R.id.person_text);
            this.cost = itemView.findViewById(R.id.cost);
            this.rate = itemView.findViewById(R.id.rate);
            this.icon = itemView.findViewById(R.id.icon);
            this.imageView = itemView.findViewById(R.id.ava);


        }
    }

    private final LayoutInflater inflater;
    private List<PortfolioPerson> items;
    private NavController navController;
    public PortfolioAdapter(Context context, NavController navController) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.portfolio_card, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PortfolioPerson item = items.get(position);
        String inputText = item.getText();
        if (inputText.length() > 165) {
            inputText = inputText.substring(0, 165) + "...";
        }
        holder.text.setText(inputText);
        holder.fio.setText(item.getName());
        holder.imageView.setImageResource(item.getPhoto());
        holder.cost.setText(item.getCost());
        holder.rate.setText(item.getRate());
        holder.icon.setImageResource(item.getIcon());
        Glide.with(holder.imageView.getContext()).load(item.getPhoto()).circleCrop().into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("portfolio", holder.fio.getText().toString());
                navController.navigate(R.id.portfolio, bundle);
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setChatPersons(List<PortfolioPerson> portfolioPersons){
        this.items.clear();
        this.items.addAll(portfolioPersons);
        Log.d("WOORK", "It's OK");
        notifyDataSetChanged();
    }
}


