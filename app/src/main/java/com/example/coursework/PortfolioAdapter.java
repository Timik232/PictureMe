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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(imageView.getContext(), fio.getText(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, fio.getText().toString());
                }
            });
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
    public PortfolioAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
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


