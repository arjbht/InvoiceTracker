package com.ab.invoicetracker.network;

import com.ab.invoicetracker.BaseApplication;
import com.ab.invoicetracker.BaseFragment;
import com.ab.invoicetracker.R;
import com.ab.invoicetracker.network.model.BaseResponse;
import com.ab.invoicetracker.network.model.InvoiceHistory.InvoiceHistoryResponse;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadRequest;
import com.ab.invoicetracker.network.model.image_upload.ImageUploadResponse;
import com.ab.invoicetracker.network.model.login.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 4/21/2021.
 */
public class NetworkCallController {
    private final BaseFragment mContext;
    private NetworkResponseListner mListner;

    public NetworkCallController(BaseFragment context) {
        this.mContext = context;
    }

    public NetworkCallController() {
        this.mContext = null;
    }

    public void setListner(NetworkResponseListner listner) {
        this.mListner = listner;
    }

    public void login(String username, String password) {

        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .getLogin(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(response.body().getData());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(response.errorBody().string());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Something went wrong, please try again !!!");
                    }
                });
    }

    public void getSearchInvoice(final String invoiceNo) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getInvoiceDetails(invoiceNo)
                    .enqueue(new Callback<InvoiceHistoryResponse>() {
                        @Override
                        public void onResponse(Call<InvoiceHistoryResponse> call,
                                               Response<InvoiceHistoryResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<InvoiceHistoryResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getInvoiceHistory(final String name) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getInvoiceHistory(name)
                    .enqueue(new Callback<InvoiceHistoryResponse>() {
                        @Override
                        public void onResponse(Call<InvoiceHistoryResponse> call,
                                               Response<InvoiceHistoryResponse> response) {
//                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
//                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<InvoiceHistoryResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveInvoiceDetails(final String invoiceId, final String imgUrl, final String userName, final String dateTime) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .saveInvoiceDetails(invoiceId, imgUrl, userName, dateTime)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call,
                                               Response<BaseResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void uploadImage(ImageUploadRequest request) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getImageUrlApi()
                    .uploadImage(request)
                    .enqueue(new Callback<ImageUploadResponse>() {
                        @Override
                        public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
