package com.std28.lib.http.interfaces;

import org.json.JSONObject;

/**
 * Created by Mauricio Barcelo on 11/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface JSONObjectResponse
    extends Response
{
    void onResponse(JSONObject object);
}
