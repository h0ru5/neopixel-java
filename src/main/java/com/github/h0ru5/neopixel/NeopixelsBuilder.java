package com.github.h0ru5.neopixel;

public class NeopixelsBuilder {
    private int number;
    private int pin = 18;
    private int freq_hz = 80000;
    private int dma = 5;
    private boolean invert = false;
    private int brightness = 255;

    public NeopixelsBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public NeopixelsBuilder setPin(int pin) {
        this.pin = pin;
        return this;
    }

    public NeopixelsBuilder setFreq_hz(int freq_hz) {
        this.freq_hz = freq_hz;
        return this;
    }

    public NeopixelsBuilder setDma(int dma) {
        this.dma = dma;
        return this;
    }

    public NeopixelsBuilder setInvert(boolean invert) {
        this.invert = invert;
        return this;
    }

    public NeopixelsBuilder setBrightness(int brightness) {
        this.brightness = brightness;
        return this;
    }

    public Neopixels createNeopixels() {
        return new Neopixels(number, pin, freq_hz, dma, invert, brightness);
    }
}