package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.chatViewModel.ChatViewModel;
import com.example.coursework.chatViewModel.ChatViewModelFactory;
import com.example.coursework.databinding.ChatsFragmentBinding;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    ChatsFragmentBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChatViewModelFactory factory = new ChatViewModelFactory(requireContext().getApplicationContext());
        chatViewModel = new ViewModelProvider(requireActivity(), factory).get(ChatViewModel.class);
        //chatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);
        binding = ChatsFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
//        chatViewModel.getAllChatPersonsLiveData().observe(getViewLifecycleOwner(), chatPersons -> {
//            RecyclerView itemsList =  view.findViewById(R.id.recyclerView);
//            LinearLayoutManager manager = new LinearLayoutManager(requireContext()); // LayoutManager
//            PersonAdapter adapter = new PersonAdapter(requireContext(), chatViewModel.getAllChatPersons()); // Создание объекта
//            Log.d("Chat", "Запустили");
//            itemsList.setLayoutManager(manager); // Назначение LayoutManager для RecyclerView
//            itemsList.setAdapter(adapter);
//            Log.d("Chat", "Прикрепили");
//        });

    }
}

