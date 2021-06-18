package com.ab.invoicetracker.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.ab.invoicetracker.BaseActivity;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.databinding.ActivitySplashBinding;
import com.ab.invoicetracker.utils.SharedPreferencesUtility;
import com.splunk.mint.Mint;

public class SplashActivity extends BaseActivity {
    ActivitySplashBinding mActivitySplashBinding;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Mint.initAndStartSession(this.getApplication(), "5f331de2");
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        mActivitySplashBinding.imgSplash.startAnimation(animation);
        SharedPreferencesUtility.savePrefBoolean(SplashActivity.this, SharedPreferencesUtility.PREF_REFRESH, false);
        PackageInfo pInfo = null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String mobileVersion = pInfo.versionName;
            mActivitySplashBinding.txtVersion.setText("V " + mobileVersion);
            mActivitySplashBinding.txtVersion.setTypeface(mActivitySplashBinding.txtVersion.getTypeface(), Typeface.BOLD);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        splashScreen();
    }

    private void splashScreen() {
        try {
            new Handler().postDelayed(() -> {

                if (SharedPreferencesUtility.getPrefBoolean(SplashActivity.this, SharedPreferencesUtility.IS_USER_LOGIN)) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }, SPLASH_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}