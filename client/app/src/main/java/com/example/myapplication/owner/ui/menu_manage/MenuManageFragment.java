package com.example.myapplication.owner.ui.menu_manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentOwnerMenuManageBinding;

public class MenuManageFragment extends Fragment {
    private MenuManageViewModel menuManageViewModel;
    private FragmentOwnerMenuManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        menuManageViewModel =
                new ViewModelProvider(this).get(MenuManageViewModel.class);
        binding = FragmentOwnerMenuManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
