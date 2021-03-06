package com.example.pkg.androidapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class StatutManagerActivity extends AppCompatActivity {

    private Switch activateSwitch;
    TextView statutDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statut_manager);

         activateSwitch = (Switch) findViewById(R.id.ActivateSwitch);
         activateSwitch.setChecked(isActivated());
         statutDescription = (TextView) findViewById(R.id.StatutDscEdit);
         SetStatutDescription(isActivated());
        activateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) smsScanning(true);
                else  smsScanning(false);
                SetStatutDescription(isChecked);
            }
        });

    }

    void SetStatutDescription(boolean set)
    {
        if (set)
        {statutDescription.setText("Sms scanning is running now"); }
        else {statutDescription.setText("Sms scanning is off now ");}
    }


    private boolean isActivated() {

        SharedPreferences prefs = getSharedPreferences(ConfigData.MY_PREFS_NAME, MODE_PRIVATE);
        boolean active = prefs.getBoolean(ConfigData.ACTIVATE, false);

        return active;
    }

    private void smsScanning(boolean active)
    {SharedPreferences settings =  getSharedPreferences(ConfigData.MY_PREFS_NAME , 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(ConfigData.ACTIVATE, active);
        editor.apply();



    }


}
