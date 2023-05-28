package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
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
        return inflater.inflate(R.layout.chats_fragment, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        PersonService personService = new PersonService();
        RecyclerView itemsList =  view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext()); // LayoutManager
        NewPersonAdapter adapter = new NewPersonAdapter(requireContext(), personService.getPersons()); // Создание объекта
        Log.d("Chat", "Запустили");
        itemsList.setLayoutManager(manager); // Назначение LayoutManager для RecyclerView
        itemsList.setAdapter(adapter);
        Log.d("Chat", "Прикрепили");
    }
}

