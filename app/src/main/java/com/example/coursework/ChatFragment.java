package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.coursework.databinding.ChatsFragmentBinding;

public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);
        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        PersonService personService = new PersonService();
        ChatsFragmentBinding binding = ChatsFragmentBinding.inflate(getLayoutInflater());
        RecyclerView itemsList =  binding.recyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(requireContext()); // LayoutManager
        NewPersonAdapter adapter = new NewPersonAdapter(requireContext(), personService.getPersons()); // Создание объекта
        //adapter.setData(personService.getPersons()); // Заполнение данными
//        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(3,1);
//        itemsList.setLayoutManager(layoutManager1);

        itemsList.setLayoutManager(manager); // Назначение LayoutManager для RecyclerView
        itemsList.setAdapter(adapter);
    }
}

