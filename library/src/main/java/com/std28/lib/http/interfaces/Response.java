package com.std28.lib.http.interfaces;

import org.json.JSONObject;


public interface Response {

    void onResponse(JSONObject object);

    void onError(String message);

}
