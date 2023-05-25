package com.example.coursework;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.databinding.ItemChatBinding;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Chat_Person> data = new ArrayList<>();

    public void setData(List<Chat_Person> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemChatBinding binding = ItemChatBinding.inflate(inflater, parent, false);
        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Chat_Person person = data.get(position);
        //holder.binding.setName(person);
        holder.binding.fio.setText(person.getName());
        holder.binding.personText.setText(person.getLast_message());
        holder.binding.personTime.setText(person.getLast_date());
        //holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        private final ItemChatBinding binding;

        public PersonViewHolder(ItemChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}



