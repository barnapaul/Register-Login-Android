package com.example.barna.shop.model;

import org.json.JSONObject;

public interface HttpCallback {

    void onSuccess(JSONObject response);

    void onError(String error);

}
