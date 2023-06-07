package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.chatViewModel.ChatViewModel;
import com.example.coursework.databinding.OneChatFragmentBinding;
import com.example.coursework.databinding.PortfolioBinding;

public class OneChat extends Fragment {
    OneChatFragmentBinding binding;
    private ChatViewModel chatViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("person");
            DataChatPerson person = chatViewModel.getChatPerson(name);
            TextView fio = binding.fio;
            fio.setText(person.getName());
            ImageView ava = binding.ava;
            Glide.with(requireContext())
                    .load(person.getPhoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ava);
        }
    }
}
