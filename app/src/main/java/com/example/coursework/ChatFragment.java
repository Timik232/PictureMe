package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coursework.databinding.ChatsFragmentBinding;

public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);
        PersonService personService = new PersonService();
        ChatsFragmentBinding binding = ChatsFragmentBinding.inflate(getLayoutInflater());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext()); // LayoutManager
        PersonAdapter adapter = new PersonAdapter(); // Создание объекта
        adapter.setData(personService.getPersons()); // Заполнение данными

        binding.recyclerView.setLayoutManager(manager); // Назначение LayoutManager для RecyclerView
        binding.recyclerView.setAdapter(adapter);

        return view;
    }
}

