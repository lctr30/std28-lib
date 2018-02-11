package com.std28.lib.http.interfaces;


public interface Response {

    void onResponse(String string);

    void onError(int code, String message);

}
