package com.ab.invoicetracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by arjun on 03/05/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void attachBaseContext(Context context) {
        try {
            super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, tag).commit();
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }


    public void showProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        Log.i("ProgressDialog", "showProgressDialog");
        try {
            mProgressDialog = new ProgressDialog(this, R.style.TransparentProgressDialog);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            if (!mProgressDialog.isShowing()) {
                return;
            }
            Log.i("ProgressDialog", "dismissProgressDialog");
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
