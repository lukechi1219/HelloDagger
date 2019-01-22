package com.lukechi.android.opendata.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllavailableLotsJson {

    @SerializedName("data")
    @Expose
    private AllavailableLotsData data;

    public AllavailableLotsData getData() {
        return data;
    }
}
