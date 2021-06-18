package com.ab.invoicetracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.invoicetracker.BaseApplication;
import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.activities.InvoiceDetailsActivity;
import com.ab.invoicetracker.adapter.RecycleInvoiceDetailsAdapter;
import com.ab.invoicetracker.adapter.RecycleInvoiceHistoryAdapter;
import com.ab.invoicetracker.databinding.FragmentInvoiceHistoryBinding;
import com.ab.invoicetracker.handler.OnListItemClickHandler;
import com.ab.invoicetracker.network.NetworkCallController;
import com.ab.invoicetracker.network.NetworkResponseListner;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryData;
import com.ab.invoicetracker.network.model.login.LoginData;

import java.util.List;

import io.realm.RealmResults;

public class InvoiceHistoryFragment extends BaseFragment {
    FragmentInvoiceHistoryBinding mFragmentInvoiceHistoryBinding;
    RecycleInvoiceHistoryAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    public InvoiceHistoryFragment() {
        // Required empty public constructor
    }


    public static InvoiceHistoryFragment newInstance() {
        InvoiceHistoryFragment fragment = new InvoiceHistoryFragment();
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
        mFragmentInvoiceHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice_history, container, false);
        return mFragmentInvoiceHistoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentInvoiceHistoryBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentInvoiceHistoryBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleInvoiceHistoryAdapter(getActivity());
        mFragmentInvoiceHistoryBinding.recyclerView.setAdapter(mAdapter);
        getInvoiceHistory();
    }

    private void getInvoiceHistory() {
        try {
            if (getActivity() != null) {
                RealmResults<LoginData> LoginRealmModels =
                        BaseApplication.getRealm().where(LoginData.class).findAll();
                if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                    String id = LoginRealmModels.get(0).getEmail();
                    NetworkCallController controller = new NetworkCallController();
                    controller.setListner(new NetworkResponseListner<List<InvoiceHistoryData>>() {

                        @Override
                        public void onResponse(List<InvoiceHistoryData> items) {
                            mAdapter.setData(items);
                            mAdapter.notifyDataSetChanged();
                            mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
                                @Override
                                public void onItemClick(int position) {
                                    startActivity(new Intent(getActivity(), InvoiceDetailsActivity.class)
                                            .putExtra(InvoiceDetailsActivity.ARGS_NAME, items.get(position).getAccountR().getName())
                                            .putExtra(InvoiceDetailsActivity.ARGS_INVOICE_NO, items.get(position).getSFDCInvoiceNumberC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_NET_COST, items.get(position).getNetCostC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_SFDC_NO, items.get(position).getName())
                                            .putExtra(InvoiceDetailsActivity.ARGS_REGION, items.get(position).getAccountR().getInvoiceBillingRegionC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_INVOICE_ID, items.get(position).getId())
                                            .putExtra(InvoiceDetailsActivity.ARGS_VERIFIED, items.get(position).getInvoiceSubmissionVerifiedC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_WRONG, items.get(position).getWrongInvoiceSubmissionC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_INQUEUE, items.get(position).getInvoice_Submission_Synced__c())
                                            .putExtra(InvoiceDetailsActivity.ARGS_DATE, items.get(position).getInvoice_Submission_Date_Time_Text())
                                            .putExtra(InvoiceDetailsActivity.ARGS_IMG, items.get(position).getInvoiceSubmissionImageC())
                                    );
                                }
                            });

                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                    controller.getInvoiceHistory(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        getInvoiceHistory();
    }
}