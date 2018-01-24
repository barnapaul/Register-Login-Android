package com.example.barna.shop.model;

import org.json.JSONArray;
import org.json.JSONObject;

public interface HttpCallback {

    void onSuccess(JSONObject response);

    void onSuccess(JSONArray response);

    void onError(String error);
}
