package com.uvn.network.model;

import java.io.Serializable;

/**
 * Created by Vladyslav Ulianytskyi on 23.01.2019.
 */
public class PairCurrency implements Serializable {
    private String name;

    public PairCurrency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PairCurrency{" +
                "name='" + name + '\'' +
                '}';
    }
}
