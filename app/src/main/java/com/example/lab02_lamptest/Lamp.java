package com.example.lab02_lamptest;

public class Lamp {
    private boolean isOn = false;
    private int intensity = 0;
    private Bulb bulb;

    public Lamp() {
        this.bulb = new Bulb();
    }

    public void turnOn() {
        isOn = true;
        if (!bulb.isBurned()) {
            if (intensity == 0) {
                intensity = 1;
            }
            bulb.turnOn();
        }
    }

    public void turnOff() {
        isOn = false;
        intensity = 0;
        bulb.turnOff();
    }

    public void brighten() {
        if (isOn) {
            intensity++;
            if (intensity > 10) {
                bulb.burn();
                bulb.turnOff();
                isOn = false;
                intensity = 0;
            }
        }
    }

    public void dim() {
        if (isOn) {
            intensity--;
            if (intensity <= 0) {
                isOn = false;
                intensity = 0;
                bulb.turnOff();
            }
        }
    }

    public boolean replaceBulb() {
        if (!isOn) {
            bulb = new Bulb();
            return true;
        }
        return false;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isShining() {
        return isOn && (intensity > 0) && bulb.isOn();
    }

    public boolean isBulbBurned() {
        return bulb.isBurned();
    }

    public int getIntensity() {
        return intensity;
    }
}
