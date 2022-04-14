package com.hamza.ieeechallenge.ui.dailymeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.Adapters.DailyMealAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.FragmentDailyMealBinding;
import com.hamza.ieeechallenge.model.DailyMealModule;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {
    private FragmentDailyMealBinding binding;
    List<DailyMealModule> dailyMealModuleList;
    DailyMealAdapter dailyMealAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDailyMealBinding.inflate(getLayoutInflater() , container , false);
        fillDailyMealList();
        setDailyMealAdapter();

        return binding.getRoot();
    }

    private void fillDailyMealList() {
        dailyMealModuleList = new ArrayList<>();
        dailyMealModuleList.add(new DailyMealModule(R.drawable.breakfast,"Breakfast","from 7 AM to 11 AM","Breakfast"));
        dailyMealModuleList.add(new DailyMealModule(R.drawable.dinner,"Dinner","from 4 PM to 7 PM","Dinner"));
        dailyMealModuleList.add(new DailyMealModule(R.drawable.launch,"Lunch","from 9 Pm to 12 AM","Lunch"));
        dailyMealModuleList.add(new DailyMealModule(R.drawable.sweets,"Sweets","Available all day","Sweets"));
        dailyMealModuleList.add(new DailyMealModule(R.drawable.coffee,"Coffee","Available all day","Coffee"));

    }

    private void setDailyMealAdapter() {
        binding.dailyMealRc.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealAdapter = new DailyMealAdapter(dailyMealModuleList,getContext());
        binding.dailyMealRc.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();
    }

}