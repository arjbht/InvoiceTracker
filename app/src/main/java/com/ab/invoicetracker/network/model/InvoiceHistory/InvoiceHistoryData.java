package com.ab.invoicetracker.network.model.InvoiceHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 6/1/2021.
 */
public class InvoiceHistoryData {
    @SerializedName("SFDC_Invoice_Number__c")
    @Expose
    private String sFDCInvoiceNumberC;
    @SerializedName("Service_Invoice_Attachment__c")
    @Expose
    private String serviceInvoiceAttachmentC;
    @SerializedName("Account__r")
    @Expose
    private Account accountR;
    @SerializedName("AttachmentId__c")
    @Expose
    private String attachmentIdC;
    @SerializedName("Net_Cost__c")
    @Expose
    private String netCostC;
    @SerializedName("Invoice_Submission_Verified__c")
    @Expose
    private Boolean invoiceSubmissionVerifiedC;
    @SerializedName("Wrong_Invoice_Submission__c")
    @Expose
    private Boolean wrongInvoiceSubmissionC;
    @SerializedName("Invoice_Submission_Synced__c")
    @Expose
    private Boolean Invoice_Submission_Synced__c;
    @SerializedName("Invoice_Submission_Image__c")
    @Expose
    private String invoiceSubmissionImageC;
    @SerializedName("Invoice_Submission_Date_Time__c")
    @Expose
    private String invoiceSubmissionDateTimeC;
    @SerializedName("Invoice_Submission_Date_Time_Text")
    @Expose
    private String Invoice_Submission_Date_Time_Text;
    @SerializedName("attributes")
    @Expose
    private Attribute attributes;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("TicketID")
    @Expose
    private Object ticketID;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getSFDCInvoiceNumberC() {
        return sFDCInvoiceNumberC;
    }

    public void setSFDCInvoiceNumberC(String sFDCInvoiceNumberC) {
        this.sFDCInvoiceNumberC = sFDCInvoiceNumberC;
    }

    public String getServiceInvoiceAttachmentC() {
        return serviceInvoiceAttachmentC;
    }

    public void setServiceInvoiceAttachmentC(String serviceInvoiceAttachmentC) {
        this.serviceInvoiceAttachmentC = serviceInvoiceAttachmentC;
    }

    public Account getAccountR() {
        return accountR;
    }

    public void setAccountR(Account accountR) {
        this.accountR = accountR;
    }

    public String getAttachmentIdC() {
        return attachmentIdC;
    }

    public void setAttachmentIdC(String attachmentIdC) {
        this.attachmentIdC = attachmentIdC;
    }

    public String getNetCostC() {
        return netCostC;
    }

    public void setNetCostC(String netCostC) {
        this.netCostC = netCostC;
    }

    public Boolean getInvoiceSubmissionVerifiedC() {
        return invoiceSubmissionVerifiedC;
    }

    public void setInvoiceSubmissionVerifiedC(Boolean invoiceSubmissionVerifiedC) {
        this.invoiceSubmissionVerifiedC = invoiceSubmissionVerifiedC;
    }

    public Boolean getWrongInvoiceSubmissionC() {
        return wrongInvoiceSubmissionC;
    }

    public void setWrongInvoiceSubmissionC(Boolean wrongInvoiceSubmissionC) {
        this.wrongInvoiceSubmissionC = wrongInvoiceSubmissionC;
    }

    public String getInvoiceSubmissionImageC() {
        return invoiceSubmissionImageC;
    }

    public void setInvoiceSubmissionImageC(String invoiceSubmissionImageC) {
        this.invoiceSubmissionImageC = invoiceSubmissionImageC;
    }

    public String getInvoiceSubmissionDateTimeC() {
        return invoiceSubmissionDateTimeC;
    }

    public void setInvoiceSubmissionDateTimeC(String invoiceSubmissionDateTimeC) {
        this.invoiceSubmissionDateTimeC = invoiceSubmissionDateTimeC;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getTicketID() {
        return ticketID;
    }

    public void setTicketID(Object ticketID) {
        this.ticketID = ticketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInvoice_Submission_Synced__c() {
        return Invoice_Submission_Synced__c;
    }

    public void setInvoice_Submission_Synced__c(Boolean invoice_Submission_Synced__c) {
        Invoice_Submission_Synced__c = invoice_Submission_Synced__c;
    }

    public String getInvoice_Submission_Date_Time_Text() {
        return Invoice_Submission_Date_Time_Text;
    }

    public void setInvoice_Submission_Date_Time_Text(String invoice_Submission_Date_Time_Text) {
        Invoice_Submission_Date_Time_Text = invoice_Submission_Date_Time_Text;
    }
}
