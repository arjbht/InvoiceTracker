package com.ab.invoicetracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ab.invoicetracker.BaseActivity;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.databinding.ActivityHomeBinding;
import com.ab.invoicetracker.fragments.HomeFragment;
import com.ab.invoicetracker.utils.SharedPreferencesUtility;

public class HomeActivity extends BaseActivity {
    ActivityHomeBinding mActivityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);
        addFragment(HomeFragment.newInstance(), "HomeActivity - HomeFragment");
        SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.IS_USER_LOGIN, true);
    }


}