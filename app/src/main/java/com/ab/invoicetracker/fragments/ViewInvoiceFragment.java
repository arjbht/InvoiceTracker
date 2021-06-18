package com.ab.invoicetracker.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.widget.Toast;

import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.databinding.FragmentViewInvoiceBinding;
import com.ab.invoicetracker.utils.DownloadTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewInvoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewInvoiceFragment extends BaseFragment {
    FragmentViewInvoiceBinding mFragmentViewInvoiceBinding;
    String URL = "http://apps.hicare.in/api/api/invoice/DownloadInvoicePdf?InvoiceNo=a2q2x000000cC2eAAE";

    public ViewInvoiceFragment() {
        // Required empty public constructor
    }

    public static ViewInvoiceFragment newInstance() {
        ViewInvoiceFragment fragment = new ViewInvoiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentViewInvoiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_invoice, container, false);
        return mFragmentViewInvoiceBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //check if your device is marshmallow that need runtime permission

        String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        int permsRequestCode = 200;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, permsRequestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean writeAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;

                //if user click allow, it will start downloading the file from URL
                if (writeAccepted) {
                    new DownloadTask(getActivity(), URL);
                } else {
                    Toast.makeText(getActivity(), "you have no permission!", Toast.LENGTH_LONG).show();
                }


                break;

        }

    }
}