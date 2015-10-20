package com.github.h0ru5.neopixel;

/**
 * Created by mchn1210 on 20.10.2015.
 */
public interface Neopixels {
    int getBrightness();

    void init();

    void render();

    void setBrightness(int brightness);

    void setColor(int num, NeoPixelColor color);

    void setColor(int num, byte red, byte green, byte blue);

    void setColor(int num, long uint_32);

    void colorWipe(NeoPixelColor color);

    NeoPixelColor getColor(int num);
}
