package com.std28.lib.http.core;

import android.util.Log;

import com.std28.lib.http.interfaces.JSONArrayResponse;
import com.std28.lib.http.interfaces.ErrorCode;
import com.std28.lib.http.interfaces.JSONObjectResponse;
import com.std28.lib.http.interfaces.Response;
import com.std28.lib.http.interfaces.StringResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BaseResponse
    implements ErrorCode
{
    public static final String TAG = "BaseResponse";

    private int code;
    private BaseRequest request;
    private String text;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private Response responseImp;
    private String errorMessage;

    public BaseResponse() {
        super();
    }

    public BaseResponse(Response httpResponse) {
        this();
        this.responseImp = httpResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public BaseRequest getRequest() {
        return request;
    }

    public void setRequest(BaseRequest request) {
        this.request = request;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JSONObject getJsonObject() {
        if (jsonObject != null) {
            return jsonObject;
        } else {
            try {
                jsonObject = new JSONObject(text);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }

    public JSONArray getJsonArray() {
        if (jsonArray != null) {
            return jsonArray;
        } else {
            try {
                jsonArray = new JSONArray(text);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void onResponse() {
        if (this.responseImp instanceof StringResponse) {
            StringResponse response = (StringResponse) this.responseImp;
            response.onResponse(getText());
            return;
        }
        if (this.responseImp instanceof JSONObjectResponse) {
            JSONObjectResponse response = (JSONObjectResponse) this.responseImp;
            response.onResponse(getJsonObject());
            return;
        }
        if (this.responseImp instanceof JSONArrayResponse) {
            JSONArrayResponse response = (JSONArrayResponse) this.responseImp;
            response.onResponse(getJsonArray());
            return;
        }
        responseImp.onError(ErrorCode.UNKNOWN, null);
    }

    public void onError(){
        if (this.responseImp != null) {
            if (this.code != OK) {
                this.responseImp.onError(this.code, this.errorMessage);
            }
        } else {
            Log.d(TAG, "No response implementation");
        }
    }



}
