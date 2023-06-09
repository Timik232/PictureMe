package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.chatViewModel.AppViewModel;
import com.example.coursework.databinding.ChatsFragmentBinding;

public class ChatFragment extends Fragment {
    private AppViewModel appViewModel;
    ChatsFragmentBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);
        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        RecyclerView itemsList =  view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        itemsList.setLayoutManager(manager);
        NavController navController = Navigation.findNavController(view);
        PersonAdapter adapter = new PersonAdapter(requireContext(), navController);
        Log.d("Chat", "Запустили");
        itemsList.setAdapter(adapter);
        Log.d("Chat", "Прикрепили");
        appViewModel.getAllChatPersonsLiveData().observe(getViewLifecycleOwner(), chatPersons -> {
            adapter.setChatPersons(chatPersons);
        });

    }
}

