package com.example.myapplication.owner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.OwnerMainBinding;
import com.example.myapplication.owner.ui.check_sales.DatePickerFragment;
import com.google.android.material.navigation.NavigationView;

public class OwnerMain extends AppCompatActivity {

    private AppBarConfiguration mAppbarConfiguration;
    private OwnerMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = OwnerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppbarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_seat, R.id.nav_menu, R.id.nav_sales)
                .setOpenableLayout(drawer).
                build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppbarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppbarConfiguration) || super.onSupportNavigateUp();
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    @SuppressLint("DefaultLocale")
    public void processDatePickerResult(int year, int month, int day){
        String year_string = Integer.toString(year);
        String day_string = String.format("%02d", day);
        String month_string = String.format("%02d", month+1);
        String dateMessage = (year_string+"-"+month_string+"-"+day_string);

        EditText text = (EditText) findViewById(R.id.check_date);
        text.setText(dateMessage);
    }
}
