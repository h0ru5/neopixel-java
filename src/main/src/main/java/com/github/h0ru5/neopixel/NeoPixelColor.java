package com.github.h0ru5.neopixel;

/**
 * Created by Johannes on 14.10.2015.
 */
public class NeoPixelColor {
    public static final NeoPixelColor RED = new NeoPixelColor((byte) 0xff, (byte) 0x0, (byte) 0x0);
    public static final NeoPixelColor GREEN = new NeoPixelColor((byte) 0x0, (byte) 0xff, (byte) 0x0);
    public static final NeoPixelColor BLUE = new NeoPixelColor((byte) 0x0, (byte) 0x0, (byte) 0xff);
    public byte red = 0;
    public byte green = 0;
    public byte blue = 0;

    public NeoPixelColor(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    public NeoPixelColor(int red, int green, int blue) {
        this.red = (byte) red;
        this.green = (byte) green;
        this.blue = (byte) blue;
    }

    public NeoPixelColor() {

    }

    public static long bytesToValue(int red, int green, int blue) {
        return (red << 16) | (green << 8) | blue;
    }

    public static NeoPixelColor fromBytes(byte red, byte green, byte blue) {
        return new NeoPixelColor(red, green, blue);
    }

    public static NeoPixelColor fromValue(long value) {
        NeoPixelColor res = new NeoPixelColor();
        res.red = (byte) (value >> 16 | 0xFF);
        res.green = (byte) (value >> 8 | 0xFF);
        res.blue = (byte) (value | 0xFF);
        return res;
    }

    public long getValue() {
        return (red << 16) | (green << 8) | blue;
    }
}
