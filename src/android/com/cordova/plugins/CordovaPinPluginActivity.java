package com.cordova.plugins;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaPinPluginActivity extends Activity {

    private EditText pinEditText;
    private Button button1, button2;

    int PIN_LENGTH = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction("CLEAR_PIN");
        registerReceiver(receiver, filter);

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        setContentView(resources.getIdentifier("cordova_pin_plugin_activity", "layout", package_name));


        pinEditText = (EditText) findViewById(getResource("pinEditText", "id"));
        pinEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == PIN_LENGTH) {
                    Intent broadcast = new Intent();
                    broadcast.setAction("PIN");
                    broadcast.putExtra("PIN",s.toString());
                    broadcast.putExtra("Button", 0);
                    sendBroadcast(broadcast);
                    pinEditText.setText("");
                }
            }
        });

        button1 = (Button) findViewById(getResource("button1", "id"));
        button2 = (Button) findViewById(getResource("button2", "id"));

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            String pinHint = b.getString("hint");
            String button1Title = b.getString("button1");
            String button2Title = b.getString("button2");

            pinEditText.setHint(pinHint);
            if (button1Title != null) {
                button1.setVisibility(View.VISIBLE);
                button1.setText(button1Title);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent broadcast = new Intent();
                        broadcast.setAction("PIN");
                        broadcast.putExtra("Button", 1);
                        broadcast.putExtra("PIN",pinEditText.getText().toString());
                        sendBroadcast(broadcast);
                    }
                });
            } else {
                button1.setVisibility(View.INVISIBLE);
                button1.setText("");
            }

            if (button2Title != null) {
                button2.setVisibility(View.VISIBLE);
                button2.setText(button2Title);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent broadcast = new Intent();
                        broadcast.setAction("PIN");
                        broadcast.putExtra("Button", 2);
                        broadcast.putExtra("PIN",pinEditText.getText().toString());
                        sendBroadcast(broadcast);
                    }
                });
            } else {
                button2.setVisibility(View.INVISIBLE);
                button2.setText("");
            }
        }
    }

    private int getResource(String name, String category) {
        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        return resources.getIdentifier(name, category, package_name);
    }

    @Override
    public void onBackPressed() {
        //Disable back
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pinEditText.setText("");
                }
            }, 50);

        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}