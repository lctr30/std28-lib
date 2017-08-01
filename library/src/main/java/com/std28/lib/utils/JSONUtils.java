package com.std28.lib.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONUtils {

    public static final String TAG = "JSONUtils";
    public static boolean DEBUG = true;
    public static boolean VERBOSE = !true;


    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        try {
            //return new String(jsonObject.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            return jsonObject.getString(key);
        } catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getString: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return defaultValue;
    }

    public static String getString(JSONObject jsonObject, String key) {
        return getString(jsonObject, key, null);
    }

    public static String getString(JSONArray array, int i) {
        try {
            return array.getString(i);
        }catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getStringFromArray: i:" + i);
            if (VERBOSE)
                e.printStackTrace();
        }
        return "";
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            if (DEBUG)
                Log.e(TAG, "getInt: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return defaultValue;
    }

    public static int getInt(JSONObject jsonObject, String key) {
        return getInt(jsonObject, key, 0);
    }

    public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
        try {
            return jsonObject.getLong(key);
        } catch (JSONException e) {
            if (DEBUG)
                Log.e(TAG, "getLong: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return defaultValue;
    }

    public static double getDouble(JSONObject jsonObject, String key) {
        return getDouble(jsonObject, key, 0d);
    }

    public static double getDouble(JSONObject jsonObject, String key, double defaultValue) {
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            if (DEBUG)
                Log.e(TAG, "getDouble: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return defaultValue;
    }

    public static long getLong(JSONObject jsonObject, String key) {
        return getInt(jsonObject, key, 0);
    }


    public static JSONObject getJSONObject(JSONArray array, int i) {
        try {
            return array.getJSONObject(i);
        }catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getJSONObject: i:" + i);
            if (VERBOSE)
                e.printStackTrace();
        }
        return new JSONObject();
    }


    public static JSONObject getJSONObject(JSONObject object, String key) {
        try {
            return object.getJSONObject(key);
        }catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getJSONObject: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONArray getJSONArray(JSONObject object, String key) {
        try {
            return object.getJSONArray(key);
        }catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getJSONArray: key:" + key);
            if (VERBOSE)
                e.printStackTrace();
        }
        return new JSONArray();
    }

    public static boolean getBoolean(JSONObject object, String key, boolean defaultValue) {
        try {
            return object.getBoolean(key);
        }catch (Exception e) {
            if (DEBUG)
                Log.e(TAG, "getBoolean: key:" + key);

            if (VERBOSE)
                e.printStackTrace();
        }
        return defaultValue;
    }

    public static boolean getBoolean(JSONObject object, String key) {
        return getBoolean(object, key, false);
    }

}
