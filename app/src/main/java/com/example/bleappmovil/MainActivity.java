package com.example.bleappmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    DeviceDisplayAdapter adapter;
    ListView devicesView;

    public static List<LogMessage> log = new ArrayList<>();

    boolean bluetoothOn = true;
    boolean bluetoothSupport = true;
    int selectedDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toggleBluetoothBtn = (Button) findViewById(R.id.toggle_bluetooth_btn);
        toggleBluetoothBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onToggleBluetoothBtnTap();
            }
        });

        Button toggleSupportBtn = (Button) findViewById(R.id.supported_btn);
        toggleSupportBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSupportedBtnTap();
            }
        });

        Button logsBtn = (Button) findViewById(R.id.logs_btn);
        logsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent callingIntent =new Intent(getApplicationContext(), LogActivity.class);
                startActivity(callingIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doPopUpMenu();
            }
        });

        adapter = new DeviceDisplayAdapter(this);

        devicesView = ((ListView)findViewById(R.id.device_listing));
        devicesView.setAdapter(adapter);
        devicesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedDevice((int) id);
            }
        });
        devicesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedDevice((int) id);
                connectDevice();
                return true;
            }
        });

        scanDevices();
        testingValues();
    }

    protected void testingValues() {
        // TESTING BULLSHIT INCOMING:

        for (int i = 0; i < 20; i++) {
            deviceFound(new Device("Name" + i, "Mac!" + i, i % 4));
        }


        for (int i = 0; i < 20; i++) {
            logEvent(new LogMessage("Log Event Message " + i, "Today", "INFO"));
        }
    }

    protected void logEvent(LogMessage msg) {
        MainActivity.log.add(msg);
    }

    public void setSelectedDevice(int selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public Device getSelectedDevice() {
        return (Device) adapter.getItem(selectedDevice);
    }

    protected void doPopUpMenu() {

        PopupMenu popup = new PopupMenu(this, findViewById(R.id.fab_menu_view));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popupmenu, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                connectDevice();
                return true;
            case R.id.menu_disconnect:
                disconnectDevice();
                return true;
            case R.id.menu_unscan:
                stopScanning();
                return true;
            case R.id.menu_scan:
                startScanning();
                return true;
            default:
                return false;
        }
    }

    protected void connectDevice() {
        Device dvc = getSelectedDevice();

        // TODO: CODIGO PARA CONECTAR A DEVICE <3



        //
    }

    protected void disconnectDevice() {
        // TODO: CODIGO PARA DESCONECTAR DE DEVICE <3




        //
    }

    protected void startScanning() {
        // TODO: CODIGO PARA COMENZAR EL SCANNING <3




        //
    }

    protected void stopScanning() {
        // TODO: CODIGO PARA PARAR EL SCANNING <3




        //
    }

    protected void scanDevices() {
        // TODO: LOGICA DE SCAN DE DEVICES, HAZ UN OBJETO Device Y MANDALO A deviceFound()



        //
    }

    public void deviceFound(final Device dvc) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(dvc);
                    devicesView.setSelection(devicesView.getCount() - 1);
                }
            });
        } catch (Exception e) {

        }
    }

    public void clearFoundDevices() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.clear();
                    devicesView.setSelection(devicesView.getCount() - 1);
                }
            });
        } catch (Exception e) {

        }
    }

    protected void updateBluetoothBtnIcon() {
        Button toggleBluetoothBtn = (Button) findViewById(R.id.toggle_bluetooth_btn);

        Drawable btOn = getResources().getDrawable(R.drawable.bluetooth);
        Drawable btOff = getResources().getDrawable(R.drawable.notooth);
        toggleBluetoothBtn.setForeground(bluetoothOn? btOn : btOff);

        ColorStateList good = ColorStateList.valueOf(getResources().getColor(R.color.good));
        ColorStateList bad = ColorStateList.valueOf(getResources().getColor(R.color.bad));
        toggleBluetoothBtn.setForegroundTintList(bluetoothOn? good : bad);
    }

    protected void onToggleBluetoothBtnTap() {
        bluetoothOn = !bluetoothOn;
        updateBluetoothBtnIcon();

        // TODO: CODIGO PARA APAGAR Y PRENDER EL BLUETOOTH <3




        //
    }

    protected void setBluetoothDisplay(boolean value) {
        bluetoothOn = value;
        updateBluetoothBtnIcon();
    }

    protected void updateSupportBtnIcon() {
        Button toggleSupportBtn = (Button) findViewById(R.id.supported_btn);

        Drawable supOn = getResources().getDrawable(R.drawable.yes);
        Drawable supOff = getResources().getDrawable(R.drawable.no);
        toggleSupportBtn.setForeground(bluetoothSupport? supOn : supOff);

        ColorStateList good = ColorStateList.valueOf(getResources().getColor(R.color.good));
        ColorStateList bad = ColorStateList.valueOf(getResources().getColor(R.color.bad));
        toggleSupportBtn.setForegroundTintList(bluetoothSupport? good : bad);
    }


    protected void onSupportedBtnTap() {
        bluetoothSupport = !bluetoothSupport;
        updateSupportBtnIcon();

        /*
            CREO QUE EL BOTON NO DEBERIA SER BOTON, PERO NO ESTOY SEGURO ENTONCES LO VOY A DEJAR ASI
            Y VOY A AGREGAR UN METODOS PARA CAMBIAR EL ESTADO A LO MALDITA SEA
         */

        // TODO: CODIGO DE SUPPORT A BLUETOOTH <3


        //
    }

    protected void setSupportDisplay(boolean value) {
        bluetoothSupport = value;
        updateSupportBtnIcon();

        /*
            CREO QUE EL BOTON NO DEBERIA SER BOTON, PERO NO ESTOY SEGURO ENTONCES LO VOY A DEJAR ASI
            Y ESTA ESTE METODO PARA CAMBIAR EL ESTADO A LO MALDITA SEA
         */
    }

    protected void doToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
