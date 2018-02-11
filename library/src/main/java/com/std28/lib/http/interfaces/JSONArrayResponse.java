package com.std28.lib.http.interfaces;

import org.json.JSONArray;

public interface JSONArrayResponse
        extends Response
{
    void onResponse(JSONArray array);

}
