package com.ab.invoicetracker.network;

/**
 * Created by arjun on 10/12/19.
 */

 public interface NetworkResponseListner<T> {
  void onResponse(T response);

  void onFailure();
}
