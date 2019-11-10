package com.example.bleappmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DeviceDisplayAdapter adapter;
    ListView devicesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onSupportedBtnTap() {

    }

    protected void onEnableBluetoothTap() {

    }

}
