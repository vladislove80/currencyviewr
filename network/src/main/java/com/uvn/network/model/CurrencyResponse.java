package com.uvn.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class CurrencyResponse<T> {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    public T data;
}
