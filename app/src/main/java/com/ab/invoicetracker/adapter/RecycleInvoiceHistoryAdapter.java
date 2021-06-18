package com.ab.invoicetracker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.invoicetracker.R;
import com.ab.invoicetracker.databinding.ItemRecyclerInvoiceHistoryBinding;
import com.ab.invoicetracker.handler.OnListItemClickHandler;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryData;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 6/2/2021.
 */
public class RecycleInvoiceHistoryAdapter extends RecyclerView.Adapter<RecycleInvoiceHistoryAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<InvoiceHistoryData> items = null;

    public RecycleInvoiceHistoryAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecycleInvoiceHistoryAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerInvoiceHistoryBinding mRecyclerInvoiceHistoryBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_invoice_history, parent, false);
        return new RecycleInvoiceHistoryAdapter.ViewHolder(mRecyclerInvoiceHistoryBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NotNull final RecycleInvoiceHistoryAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemRecyclerInvoiceHistoryBinding.txtName.setText(items.get(position).getAccountR().getName());
            holder.mItemRecyclerInvoiceHistoryBinding.txtName.setTypeface(holder.mItemRecyclerInvoiceHistoryBinding.txtName.getTypeface(), Typeface.BOLD);
            holder.mItemRecyclerInvoiceHistoryBinding.txtNetCost.setText("₹ " + items.get(position).getNetCostC());
            holder.mItemRecyclerInvoiceHistoryBinding.txtSfdcNo.setText(items.get(position).getName());
            holder.mItemRecyclerInvoiceHistoryBinding.txtRegion.setText(items.get(position).getAccountR().getInvoiceBillingRegionC());
            holder.mItemRecyclerInvoiceHistoryBinding.txtInvNo.setText(items.get(position).getSFDCInvoiceNumberC());
            if (items.get(position).getInvoiceSubmissionImageC() != null) {
                Picasso.get().load(items.get(holder.getAdapterPosition()).getInvoiceSubmissionImageC()).into(holder.mItemRecyclerInvoiceHistoryBinding.imgInvoice);
                holder.mItemRecyclerInvoiceHistoryBinding.imgInvoicePlaceHolder.setVisibility(View.GONE);
            } else {
                holder.mItemRecyclerInvoiceHistoryBinding.imgInvoicePlaceHolder.setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickHandler.onItemClick(position);
                }
            });
            if (items.get(position).getInvoice_Submission_Synced__c() && items.get(position).getInvoiceSubmissionVerifiedC() && items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setText("Not Verified");
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setTextColor(mContext.getColor(R.color.lightRed));
                holder.mItemRecyclerInvoiceHistoryBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_not));
            } else if (items.get(position).getInvoice_Submission_Synced__c() && items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setText("Verified");
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setTextColor(mContext.getColor(R.color.themeColor));
                holder.mItemRecyclerInvoiceHistoryBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ststus_border));
            } else if (items.get(position).getInvoice_Submission_Synced__c() && !items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setText("In-Queue");
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setTextColor(mContext.getColor(R.color.colorBlue));
                holder.mItemRecyclerInvoiceHistoryBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_blue));
            } else if (!items.get(position).getInvoice_Submission_Synced__c() && !items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setText("Pending");
                holder.mItemRecyclerInvoiceHistoryBinding.txtStatus.setTextColor(mContext.getColor(R.color.late_pink));
                holder.mItemRecyclerInvoiceHistoryBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_pending));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setData(List<InvoiceHistoryData> data) {
        items.clear();
        items.addAll(data);
    }

    public InvoiceHistoryData getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecyclerInvoiceHistoryBinding mItemRecyclerInvoiceHistoryBinding;

        public ViewHolder(ItemRecyclerInvoiceHistoryBinding mItemRecyclerInvoiceHistoryBinding) {
            super(mItemRecyclerInvoiceHistoryBinding.getRoot());
            this.mItemRecyclerInvoiceHistoryBinding = mItemRecyclerInvoiceHistoryBinding;
        }
    }

}


