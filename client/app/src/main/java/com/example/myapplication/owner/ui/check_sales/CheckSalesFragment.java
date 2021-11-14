package com.example.myapplication.owner.ui.check_sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentOwnerCheckSalesBinding;

public class CheckSalesFragment extends Fragment {
    private CheckSalesViewModel checkSalesViewModel;
    private FragmentOwnerCheckSalesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkSalesViewModel =
                new ViewModelProvider(this).get(CheckSalesViewModel.class);

        binding = FragmentOwnerCheckSalesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
