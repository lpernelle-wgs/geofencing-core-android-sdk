package com.webgeoservices.woosmapgeofencingcore;

import android.content.Context;
import android.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import javax.annotation.Nullable;

public class APIHelper {
    private Context context;
    private static APIHelper _instance;

    protected APIHelper(Context context){
        this.context = context;
    }

    protected static APIHelper getInstance(Context context){
        if (_instance == null){
            _instance = new APIHelper(context);
        }
        return _instance;
    }

    protected StringRequest createGetReuqest(String url,
                                  Response.Listener<String> listener,
                                  @Nullable Response.ErrorListener errorListener){
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url,listener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new ArrayMap<>();
                headers.put("X-Api-Key", WoosmapSettingsCore.privateKeyWoosmapAPI);
                headers.put("X-Android-Identifier", context.getPackageName());
                headers.put("X-SDK-Source", "geofence-sdk");
                headers.put("X-AK-SDK-Platform", "Android");
                headers.put("X-AK-SDK-Version", WoosmapSettingsCore.getGeofencingSDKVersion(context));
                return headers;
            }
        };
        return stringRequest;
    }
}
