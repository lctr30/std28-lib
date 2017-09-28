package com.std28.lib.http.interfaces;

import org.json.JSONArray;

public interface ArrayResponse
        extends Response
{

    void onArrayResponse(JSONArray array);

}
