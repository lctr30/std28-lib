package com.std28.lib.http.core;

import android.util.Log;

import com.std28.lib.http.interfaces.ArrayResponse;
import com.std28.lib.http.interfaces.ErrorCode;
import com.std28.lib.http.interfaces.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BaseResponse
    implements ErrorCode
{
    static final String TAG = "BaseResponse";

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
        try {
            JSONObject jsonObject = new JSONObject(text);
            setJsonObject(jsonObject);
        } catch (JSONException ex1) {
            try {
                JSONArray jsonArray = new JSONArray(text);
                setJsonArray(jsonArray);
            } catch (JSONException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void onResponse(JSONObject object) {
        if (this.responseImp != null) {
            this.responseImp.onResponse(object);
        } else {
            Log.d(TAG, "No response implementation");
        }
    }

    public void onArrayResponse(JSONArray array) {
        if (this.responseImp != null) {
            ArrayResponse arrayResponse = (ArrayResponse) this.responseImp;
            arrayResponse.onArrayResponse(array);
        } else {
            Log.d(TAG, "No response implementation");
        }
    }


    public boolean isArrayResponse() {
        return this.responseImp instanceof ArrayResponse;
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
