package com.example.bleappmovil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.bleappmovil.adapters.CharDisplayAdapter;
import com.example.bleappmovil.adapters.DeviceDisplayAdapter;
import com.example.bleappmovil.R;
import com.example.bleappmovil.adapters.ServiceDisplayAdapter;
import com.example.bleappmovil.fragments.ServiceFragment;
import com.example.bleappmovil.objects.Characteristic;
import com.example.bleappmovil.objects.Device;
import com.example.bleappmovil.objects.LogMessage;
import com.example.bleappmovil.objects.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    DeviceDisplayAdapter deviceAdapter;
    ServiceDisplayAdapter serviceAdapter;
    CharDisplayAdapter charAdapter;
    ListView devicesView;
    ListView servicesView;
    ListView charView;

    public static List<LogMessage> log = new ArrayList<>();

    boolean bluetoothOn = true;
    boolean bluetoothSupport = true;
    int selectedDevice;
    int selectedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_main);


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
                Intent callingIntent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(callingIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doPopUpMenu();
            }
        });

        deviceAdapter = new DeviceDisplayAdapter(this);
        charAdapter = new CharDisplayAdapter(this);
        serviceAdapter = new ServiceDisplayAdapter(this);

        devicesView = ((ListView) findViewById(R.id.device_listing));
        devicesView.setAdapter(deviceAdapter);
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
        // TESTING BULLSH*T INCOMING:

        for (int i = 0; i < 20; i++) {
            deviceFound(new Device("Name" + i, "Mac!" + i, i % 4));
        }


        for (int i = 0; i < 20; i++) {
            logEvent(new LogMessage("Log Event Message " + i, "Today", "INFO"));
        }
    }

    protected void testingValuesServices() {
        // TESTING BULLSH*T INCOMING:

        for (int i = 0; i < 10; i++) {
            serviceFound(new Service("Service # " + i, "INFO"));
        }
    }

    protected void testingValuesChars() {
        // TESTING BULLSH*T INCOMING:
        for (int i = 0; i < 10; i++) {
            charFound(new Characteristic("Characteristic # " + i, "Testing", "00"+i,":)","R W N"));
        }
    }

    protected void logEvent(LogMessage msg) {
        MainActivity.log.add(msg);
    }

    public void setSelectedDevice(int sel) {
        this.selectedDevice = sel;
    }

    public void setSelectedService(int sel) {
        this.selectedService = sel;
    }

    public Device getSelectedDevice() {
        return (Device) deviceAdapter.getItem(selectedDevice);
    }

    public Service getSelectedService() {
        return (Service) serviceAdapter.getItem(selectedService);
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

    protected void openServicesFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ServiceFragment fragment = new ServiceFragment();
        fragmentTransaction.add(R.id.service_viewer, fragment);
        fragmentTransaction.commit();

        serviceAdapter.clear();

        servicesView = ((ListView)findViewById(R.id.services_listing));
        servicesView.setAdapter(serviceAdapter);
        servicesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedService((int) id);
                openCharFragment();
                return true;
            }
        });
        testingValuesServices();
    }

    protected void openCharFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ServiceFragment fragment = new ServiceFragment();
        fragmentTransaction.add(R.id.char_viewer, fragment);
        fragmentTransaction.commit();

        charAdapter.clear();

        charView = ((ListView)findViewById(R.id.char_listing));
        charView.setAdapter(charAdapter);
        testingValuesChars();
    }

    protected void connectDevice() {
        Device dvc = getSelectedDevice();

        // TODO: CODIGO PARA CONECTAR A DEVICE <3



        //

        openServicesFragment();
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

    public void serviceFound(final Service item) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    serviceAdapter.add(item);
                    servicesView.setSelection(servicesView.getCount() - 1);
                }
            });
        } catch (Exception e) {

        }
    }

    public void charFound(final Characteristic item) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    charAdapter.add(item);
                    charView.setSelection(charView.getCount() - 1);
                }
            });
        } catch (Exception e) {

        }
    }

    public void deviceFound(final Device item) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    deviceAdapter.add(item);
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
                    deviceAdapter.clear();
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
