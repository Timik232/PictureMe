package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.Message;
import com.example.coursework.chatViewModel.AppViewModel;
import com.example.coursework.databinding.OneChatFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OneChat extends Fragment {
    OneChatFragmentBinding binding;
    private AppViewModel appViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.portfolio, container, false);
        binding = OneChatFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataChatPerson person;
        Bundle bundle = getArguments();
        String name = bundle.getString("person");
        person = appViewModel.getChatPerson(name);
        TextView fio = binding.fio;
        fio.setText(person.getName());
        ImageView ava = binding.ava;
        Glide.with(requireContext())
                    .load(person.getPhoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ava);

        RecyclerView itemsList =  view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        Log.d("where", "work");
        itemsList.setLayoutManager(manager);
        ChatAdapter adapter = new ChatAdapter(requireContext(), binding.fio.getText().toString());
        itemsList.setAdapter(adapter);
        Log.d("Chat", "Прикрепили");
        appViewModel.getChatMessages(person.getId()).observe(getViewLifecycleOwner(), messages -> {
            adapter.setChatMessages(messages);
        });
        ImageView button = binding.sendButton;
        EditText answer = binding.sending;
        button.setOnClickListener(v ->{
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(currentDate);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String formattedTime = timeFormat.format(currentDate);
            if (!answer.getText().toString().equals("")) {
                appViewModel.saveMessage(new Message(
                        appViewModel.getUserName(), person.getId(), answer.getText().toString(),
                        formattedDate, formattedTime
                ));
                answer.setText("");
                new Thread(() -> {
                    appViewModel.setLastMessage(person.getName(), appViewModel.getLastMessage(person.getId()));
                    appViewModel.setLastDate(person.getName(), formattedTime);
                }).start();
            }
        });
    }
}
