package com.ab.invoicetracker.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.invoicetracker.BaseApplication;
import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.activities.InvoiceDetailsActivity;
import com.ab.invoicetracker.activities.LoginActivity;
import com.ab.invoicetracker.adapter.InvoiceViewPagerAdapter;
import com.ab.invoicetracker.databinding.FragmentHomeBinding;
import com.ab.invoicetracker.network.model.login.LoginData;
import com.ab.invoicetracker.utils.SharedPreferencesUtility;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    FragmentHomeBinding mFragmentHomeBinding;
    private InvoiceViewPagerAdapter mAdapter;
    private int page = 0;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            RealmResults<LoginData> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginData.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                mFragmentHomeBinding.txtLoginBy.setText("(user : " + LoginRealmModels.get(0).getEmail() + ")");
            }
        }
        setViewPager();
        onPageChange();
        mFragmentHomeBinding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(getString(R.string.logout_exit));
                dialog.setMessage(getString(R.string.logout_do_you_really_want_to_exit));
                dialog.setPositiveButton(getString(R.string.yes), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_USER_LOGIN,
                            false);
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                });
                dialog.setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.dismiss());
                dialog.show();
            }
        });
    }

    private void onPageChange() {
        try {
            mFragmentHomeBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onPageSelected(int position) {
                    page = position;
                    switch (position) {
                        case 0:
                            ((InvoiceDetailsFragment) mAdapter.getItem(position)).refresh();
                            break;
                        case 1:
                            ((InvoiceHistoryFragment) mAdapter.getItem(position)).refresh();
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewPager() {
        try {
            mFragmentHomeBinding.viewPager.setOffscreenPageLimit(2);
            mAdapter = new InvoiceViewPagerAdapter(getChildFragmentManager(), getActivity());
            mAdapter.addFragment(InvoiceDetailsFragment.newInstance(), "Invoice Details");
            mAdapter.addFragment(InvoiceHistoryFragment.newInstance(), "Invoice History");
            mFragmentHomeBinding.viewpagertab.setDistributeEvenly(true);
            mFragmentHomeBinding.viewPager.setAdapter(mAdapter);
            mFragmentHomeBinding.viewpagertab.setViewPager(mFragmentHomeBinding.viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}