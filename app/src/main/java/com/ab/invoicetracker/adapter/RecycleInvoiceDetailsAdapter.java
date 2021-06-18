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
import com.ab.invoicetracker.databinding.ItemRecyclerInvoiceDetailsBinding;
import com.ab.invoicetracker.handler.OnListItemClickHandler;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryData;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 6/2/2021.
 */
public class RecycleInvoiceDetailsAdapter extends RecyclerView.Adapter<RecycleInvoiceDetailsAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<InvoiceHistoryData> items = null;

    public RecycleInvoiceDetailsAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecycleInvoiceDetailsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerInvoiceDetailsBinding mItemRecyclerInvoiceDetailsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_invoice_details, parent, false);
        return new RecycleInvoiceDetailsAdapter.ViewHolder(mItemRecyclerInvoiceDetailsBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NotNull final RecycleInvoiceDetailsAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemRecyclerInvoiceDetailsBinding.txtName.setText(items.get(position).getAccountR().getName());
            holder.mItemRecyclerInvoiceDetailsBinding.txtName.setTypeface(holder.mItemRecyclerInvoiceDetailsBinding.txtName.getTypeface(), Typeface.BOLD);
            holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setTypeface(holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.getTypeface(), Typeface.BOLD);
            holder.mItemRecyclerInvoiceDetailsBinding.txtNetCost.setText("₹ " + items.get(position).getNetCostC());
            holder.mItemRecyclerInvoiceDetailsBinding.txtSfdcNo.setText(items.get(position).getName());
            holder.mItemRecyclerInvoiceDetailsBinding.txtRegion.setText(items.get(position).getAccountR().getInvoiceBillingRegionC());
            if (items.get(position).getInvoiceSubmissionImageC() != null) {
                Picasso.get().load(items.get(holder.getAdapterPosition()).getInvoiceSubmissionImageC()).into(holder.mItemRecyclerInvoiceDetailsBinding.imgInvoice);
                holder.mItemRecyclerInvoiceDetailsBinding.imgInvoicePlaceHolder.setVisibility(View.GONE);
            } else {
                holder.mItemRecyclerInvoiceDetailsBinding.imgInvoicePlaceHolder.setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickHandler.onItemClick(position);
                }
            });
            if (items.get(position).getInvoice_Submission_Synced__c() && items.get(position).getInvoiceSubmissionVerifiedC() && items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setText("Not Verified");
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setTextColor(mContext.getColor(R.color.lightRed));
                holder.mItemRecyclerInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_not));
            } else if (items.get(position).getInvoice_Submission_Synced__c() && items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()) {
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setText("Verified");
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setTextColor(mContext.getColor(R.color.themeColor));
                holder.mItemRecyclerInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ststus_border));
            } else if(items.get(position).getInvoice_Submission_Synced__c() && !items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()){
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setText("In-Queue");
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setTextColor(mContext.getColor(R.color.colorBlue));
                holder.mItemRecyclerInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_blue));
            }else if(!items.get(position).getInvoice_Submission_Synced__c() && !items.get(position).getInvoiceSubmissionVerifiedC() && !items.get(position).getWrongInvoiceSubmissionC()){
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setText("Pending");
                holder.mItemRecyclerInvoiceDetailsBinding.txtStatus.setTextColor(mContext.getColor(R.color.late_pink));
                holder.mItemRecyclerInvoiceDetailsBinding.lnrStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.status_border_pending));
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

    public void filterList(ArrayList<InvoiceHistoryData> filterllist) {
        items.clear();
        items.addAll(filterllist);
    }

    public void setData(List<InvoiceHistoryData> data) {
        items.clear();
        items.addAll(data);
    }

    public InvoiceHistoryData getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecyclerInvoiceDetailsBinding mItemRecyclerInvoiceDetailsBinding;

        public ViewHolder(ItemRecyclerInvoiceDetailsBinding mItemRecyclerInvoiceDetailsBinding) {
            super(mItemRecyclerInvoiceDetailsBinding.getRoot());
            this.mItemRecyclerInvoiceDetailsBinding = mItemRecyclerInvoiceDetailsBinding;
        }
    }

}


