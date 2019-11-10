package com.example.bleappmovil;

public class Device {
    private String name;
    private String mac;
    private int signal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getSignal() {
        return signal;
    }

    public String getSignalString() {
        return signal == 0? "None" :
                signal == 1? "Low" :
                        signal == 2? "Medium" :
                                "High";
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }
}
