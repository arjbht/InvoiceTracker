package com.ab.invoicetracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.activities.InvoiceDetailsActivity;
import com.ab.invoicetracker.adapter.RecycleInvoiceDetailsAdapter;
import com.ab.invoicetracker.databinding.FragmentInvoiceDetailsBinding;
import com.ab.invoicetracker.handler.OnListItemClickHandler;
import com.ab.invoicetracker.network.NetworkCallController;
import com.ab.invoicetracker.network.NetworkResponseListner;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryData;

import java.util.ArrayList;
import java.util.List;


public class InvoiceDetailsFragment extends BaseFragment {
    FragmentInvoiceDetailsBinding mFragmentInvoiceDetailsBinding;
    RecycleInvoiceDetailsAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    public InvoiceDetailsFragment() {
        // Required empty public constructor
    }

    public static InvoiceDetailsFragment newInstance() {
        InvoiceDetailsFragment fragment = new InvoiceDetailsFragment();
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
        mFragmentInvoiceDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice_details, container, false);
        return mFragmentInvoiceDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentInvoiceDetailsBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentInvoiceDetailsBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleInvoiceDetailsAdapter(getActivity());
        mFragmentInvoiceDetailsBinding.recyclerView.setAdapter(mAdapter);

        getSearchInvoice();


    }

    private void filter(String text, List<InvoiceHistoryData> items) {
        // creating a new array list to filter our data.
        ArrayList<InvoiceHistoryData> filteredlist = new ArrayList<>();
        filteredlist.addAll(items);


        if (filteredlist.isEmpty()) {
            Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.filterList(filteredlist);
            mAdapter.notifyDataSetChanged();
        }
    }


    private void getSearchInvoice() {
        try {
            mFragmentInvoiceDetailsBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchInvoiceDetails(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchInvoiceDetails(String newText) {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<List<InvoiceHistoryData>>() {
                @Override
                public void onResponse(List<InvoiceHistoryData> items) {
                    if (items != null) {
                        filter(newText, items);
                        mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
                            @Override
                            public void onItemClick(int position) {
                                    startActivity(new Intent(getActivity(), InvoiceDetailsActivity.class)
                                            .putExtra(InvoiceDetailsActivity.ARGS_NAME, items.get(position).getAccountR().getName())
                                            .putExtra(InvoiceDetailsActivity.ARGS_INVOICE_NO, items.get(position).getSFDCInvoiceNumberC())
                                            .putExtra(InvoiceDetailsActivity.ARGS_NET_COST, "₹ " + items.get(position).getNetCostC())
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
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getSearchInvoice(newText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        if (!mFragmentInvoiceDetailsBinding.search.getQuery().toString().equals("")) {
            searchInvoiceDetails(mFragmentInvoiceDetailsBinding.search.getQuery().toString());
        }
    }

}