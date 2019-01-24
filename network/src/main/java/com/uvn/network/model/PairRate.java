package com.uvn.network.model;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairRate {
    private final PairCurrency currency;
    private final String value;

    public PairRate(PairCurrency currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public PairCurrency getCurrency() {
        return currency;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return currency.getName().substring(0, 3) + "/" + currency.getName().substring(3, 6);
    }
}
