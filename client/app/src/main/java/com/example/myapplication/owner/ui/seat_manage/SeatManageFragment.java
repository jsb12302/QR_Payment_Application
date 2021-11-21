package com.example.myapplication.owner.ui.seat_manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentOwnerSeatManageBinding;


public class SeatManageFragment extends Fragment {

    private SeatManageViewModel seatManageViewModel;
    private FragmentOwnerSeatManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seatManageViewModel =
                new ViewModelProvider(this).get(SeatManageViewModel.class);
        binding = FragmentOwnerSeatManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
