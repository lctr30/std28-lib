package com.std28.lib.http.core;

import android.util.Log;

import com.std28.lib.R;
import com.std28.lib.app.BaseApp;
import com.std28.lib.http.interfaces.Response;

import org.json.JSONObject;


public class BaseResponse
{
    public static final String TAG = "BaseResponse";


    public enum Code {
        OK,
        TIMEOUT,
        UNKNOWN,
        PARSER,
        SERVER,
        NO_INTERNET
    }

    private Code code;
    private BaseRequest request;
    private String text;
    private JSONObject jsonObject;
    private Response responseImp;

    public BaseResponse() {
        super();
    }

    public BaseResponse(Response httpResponse) {
        this();
        this.responseImp = httpResponse;
    }

    private int errorCode;
    private String errorMessage;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
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
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private String getString(int resId) {
        return BaseApp.getContext().getString(resId);
    }


    public String getErrorMessage() {

        switch (this.code) {
            case OK:
                return "";
            case TIMEOUT:
                return getString(R.string.error_msg_timeout);
            case UNKNOWN:
                return getString(R.string.error_msg_unknown);
            case PARSER:
                return getString(R.string.error_msg_parser);
            case SERVER:
                return errorMessage;
            case NO_INTERNET:
                return getString(R.string.error_msg_no_internet);
        }
        return "";
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

    public void onError(String message){
        if (this.responseImp != null) {
            if (this.code != Code.OK) {
                this.responseImp.onError(getErrorMessage());
            } else {
                this.responseImp.onError(message);
            }
        } else {
            Log.d(TAG, "No response implementation");
        }
    }



}
