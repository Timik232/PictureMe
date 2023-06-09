package com.example.coursework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.chatViewModel.AppViewModel;
import com.example.coursework.databinding.PortfolioBinding;

import java.util.ArrayList;
import java.util.List;

public class Portfolio extends Fragment {
    private static final String BASE_URL = "https://api.unsplash.com/";
    private static final String ACCESS_KEY = "CFtVzrPAZxDoWAyt2C-XRFIdaVW84vPYRc1SHL42FMY";
    PortfolioBinding binding;
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
        binding = PortfolioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("portfolio");
            PortfolioPerson portfolio = appViewModel.getPortfolio(name);
            TextView fio = binding.fio;
            fio.setText(portfolio.getName());
            TextView rate = binding.rate;
            rate.setText(portfolio.getRate());
            TextView text = binding.personText;
            text.setText(portfolio.getText());
            TextView cost = binding.cost;
            cost.setText(portfolio.getCost());
            ImageView ava = binding.ava;
            Glide.with(requireContext())
                    .load(portfolio.getPhoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ava);
        }
        ViewPager2 viewPager = binding.viewPager;
        List<Integer> photoList = new ArrayList<>();
        photoList.add(R.drawable.nastya);
        photoList.add(R.drawable.ava);
        photoList.add(R.drawable.valera);
        photoList.add(R.drawable.masha);
        photoList.add(R.drawable.denis);
        photoList.add(R.drawable.roman);
        photoList.add(R.drawable.dasha);
        PhotoAdapter adapter = new PhotoAdapter(photoList);
        viewPager.setAdapter(adapter);
        Button chat = binding.open;
        chat.setOnClickListener(v ->{
            NavController navController = Navigation.findNavController(view);
            Bundle newBundle = new Bundle();
            newBundle.putString("person",binding.fio.getText().toString());
            navController.navigate(R.id.one_chat, newBundle);
        });
    }

}
