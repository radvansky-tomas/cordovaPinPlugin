package com.cordova.plugins;

import android.app.Activity;
import android.os.Bundle;

public class CordovaPinPluginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String package_name = getApplication().getPackageName();
        setContentView(getApplication().getResources().getIdentifier("cordova_pin_plugin_activity", "layout", package_name));
    }
}