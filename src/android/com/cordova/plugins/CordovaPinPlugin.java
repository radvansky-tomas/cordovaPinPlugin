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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


public class CordovaPinPlugin extends CordovaPlugin {
    private static final String TAG = "CordovaPinPlugin";
    private CordovaInterface cordova;
    private CallbackContext callbackContext;

    int PIN_ACTIVITY_CODE = 100;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;
        Log.d(TAG, "Initializing CordovaPinPlugin");
        IntentFilter filter = new IntentFilter();
        filter.addAction("PIN");
        cordova.getActivity().getApplicationContext().registerReceiver(receiver, filter);

    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        Context context = cordova.getActivity().getApplicationContext();
        this.callbackContext = callbackContext;

        if (action.equals("showPin")) {
            this.openNewActivity(context, args.getJSONObject(0).getString("hint"), args.getJSONObject(0).getString("button1"), args.getJSONObject(0).getString("button2"));
            return true;
        }

        if (action.equals("closePin")) {
            cordova.getActivity().finishActivity(PIN_ACTIVITY_CODE);
            return true;
        }

        return false;
    }

    private void openNewActivity(Context context, String hint, String button1, String button2) {
        Intent intent = new Intent(context, CordovaPinPluginActivity.class);
        if (hint != null) {
            intent.putExtra("hint", hint);
        }
        if (button1 != null) {
            intent.putExtra("button1", button1);
        }
        if (button2 != null) {
            intent.putExtra("button2", button2);
        }

        this.cordova.getActivity().startActivityForResult(intent, PIN_ACTIVITY_CODE);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(cordova.getActivity(), "received", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    callbackContext.success(1);
                }
            }, 200);

        }
    };

    public Bundle onSaveInstanceState()
    {
        Bundle state = new Bundle();
        return state;
    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext)
    {
        this.callbackContext = callbackContext;
    }

}
