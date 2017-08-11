/**
 */
package com.cordova.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;


public class CordovaPinPlugin extends CordovaPlugin {
    private static final String TAG = "CordovaPinPlugin";
    private CordovaInterface cordova;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;
        Log.d(TAG, "Initializing CordovaPinPlugin");
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        Context context = cordova.getActivity().getApplicationContext();
        if (action.equals("showPin")) {
            this.openNewActivity(context);
            return true;
        }
        return false;
    }

    private void openNewActivity(Context context) {
        Intent intent = new Intent(context, CordovaPinPluginActivity.class);
        this.cordova.getActivity().startActivity(intent);
    }
}
