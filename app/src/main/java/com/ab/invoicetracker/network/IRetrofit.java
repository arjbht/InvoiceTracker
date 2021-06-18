package com.ab.invoicetracker.network;

import com.ab.invoicetracker.network.model.BaseResponse;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryResponse;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadRequest;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadResponse;
import com.ab.invoicetracker.network.model.login.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Arjun Bhatt on 5/30/2021.
 */
public interface IRetrofit {
    String BASE_URL = "http://apps.hicare.in/api/api/";
    String IMAGE_URL = "http://connect.hicare.in/b2bwow/api/";

    /*[User Login]*/
    @GET("Authentication/InvoiceTrackerLogin")
    Call<LoginResponse> getLogin(@Query("username") String username, @Query("password") String password);

    /*[invoice/GetInvoiceDetails]*/
    @GET("invoice/GetInvoiceDetails")
    Call<InvoiceHistoryResponse> getInvoiceDetails(@Query("invoiceno") String userId);

    /*[invoice/GetInvoiceSubmissionHistory]*/
    @GET("invoice/GetInvoiceSubmissionHistory")
    Call<InvoiceHistoryResponse> getInvoiceHistory(@Query("userName") String userId);

    /*[invoice/GetInvoiceSubmissionHistory]*/
    @GET("invoice/SaveInvoiceSubmissionDetails")
    Call<BaseResponse> saveInvoiceDetails(@Query("invoiceId") String invoiceId, @Query("imageUrl") String imageUrl, @Query("userName") String userName, @Query("submittedDateTime") String submittedDateTime);

//    /*[invoice/GetInvoiceSubmissionHistory]*/
//    @GET("invoice/DownloadInvoicePdf")
//    Call<BaseResponse> downloadInvoice(@Query("InvoiceNo") String invoiceNo);

    @GET("invoice/DownloadInvoicePdf")
    Call<ResponseBody> downloadInvoice(@Query("InvoiceNo") String invoiceNo);

    /*[Attachment/UploadAttachment]*/
    @POST("Attachment/UploadAttachment")
    Call<ImageUploadResponse> uploadImage(@Body ImageUploadRequest request);
}

