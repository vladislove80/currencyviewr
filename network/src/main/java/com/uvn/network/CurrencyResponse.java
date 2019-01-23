package com.uvn.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
class CurrencyResponse<T> {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    T data;
}
