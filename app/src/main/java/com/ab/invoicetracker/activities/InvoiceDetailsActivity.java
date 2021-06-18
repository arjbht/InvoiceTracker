package com.ab.invoicetracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ab.invoicetracker.BaseActivity;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.databinding.ActivityInvoiceDetailsBinding;
import com.ab.invoicetracker.fragments.InvoiceDetailsDataFragment;

import java.util.Objects;

public class InvoiceDetailsActivity extends BaseActivity {
    ActivityInvoiceDetailsBinding mActivityInvoiceDetailsBinding;
    public static final String ARGS_NAME = "ARGS_NAME";
    public static final String ARGS_SFDC_NO = "ARGS_SFDC_NO";
    public static final String ARGS_REGION = "ARGS_REGION";
    public static final String ARGS_NET_COST = "ARGS_NET_COST";
    public static final String ARGS_INVOICE_NO = "ARGS_INVOICE_NO";
    public static final String ARGS_INVOICE_ID = "ARGS_INVOICE_ID";
    public static final String ARGS_VERIFIED = "ARGS_VERIFIED";
    public static final String ARGS_WRONG = "ARGS_WRONG";
    public static final String ARGS_INQUEUE = "ARGS_INQUEUE";
    public static final String ARGS_IMG = "ARGS_IMG";
    public static final String ARGS_DATE = "ARGS_DATE";
    private String invoiceName = "";
    private String sfdcNo = "";
    private String region = "";
    private String netCost = "";
    private String invoiceNo = "";
    private String invoiceId = "";
    private String date = "";
    private String img = "";
    private boolean verified = false;
    private boolean wrong = false;
    private boolean inQueue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityInvoiceDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_invoice_details);
        invoiceName = getIntent().getStringExtra(ARGS_NAME);
        sfdcNo = getIntent().getStringExtra(ARGS_SFDC_NO);
        region = getIntent().getStringExtra(ARGS_REGION);
        netCost = getIntent().getStringExtra(ARGS_NET_COST);
        invoiceNo = getIntent().getStringExtra(ARGS_INVOICE_NO);
        invoiceId = getIntent().getStringExtra(ARGS_INVOICE_ID);
        inQueue = getIntent().getBooleanExtra(ARGS_INQUEUE, false);
        date = getIntent().getStringExtra(ARGS_DATE);
        img = getIntent().getStringExtra(ARGS_IMG);
        verified = getIntent().getBooleanExtra(ARGS_VERIFIED, false);
        wrong = getIntent().getBooleanExtra(ARGS_WRONG, false);


        addFragment(InvoiceDetailsDataFragment.newInstance(invoiceName, sfdcNo, region, netCost, invoiceNo, invoiceId, verified, wrong, inQueue, date, img), "AddTaskActivity - AddTaskFragment");
        setSupportActionBar(mActivityInvoiceDetailsBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        try {
            getBack();
//            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void getBack() {
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("fragments", String.valueOf(fragment));
        if (fragment < 1) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }
}