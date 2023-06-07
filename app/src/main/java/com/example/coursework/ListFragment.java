package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.chatViewModel.ChatViewModel;
import com.example.coursework.databinding.ChatsFragmentBinding;

public class ListFragment extends Fragment {

    private ChatViewModel chatViewModel;
    ChatsFragmentBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photographers_fragment, container, false);
        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        RecyclerView itemsList =  view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        itemsList.setLayoutManager(manager);
        PortfolioAdapter adapter = new PortfolioAdapter(requireContext());
        Log.d("Chat", "Запустили");
        itemsList.setAdapter(adapter);
        Log.d("Chat", "Прикрепили");
        chatViewModel.getAllPortfolioLiveData().observe(getViewLifecycleOwner(), portfolioPeople -> {
            adapter.setChatPersons(portfolioPeople);
        });

    }
}