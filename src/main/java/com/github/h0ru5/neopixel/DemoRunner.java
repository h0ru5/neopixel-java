package com.github.h0ru5.neopixel;

/**
 * Created by mchn1210 on 19.10.2015.
 */
public class DemoRunner {
    public static void main(String[] args) {
        System.loadLibrary("rpi_ws281x");

        Neopixels np = NeopixelsImpl.createWithDefaults(64);
        np.render();

        boolean swapped = false;
        NeoPixelColor color = NeoPixelColor.RED;
        np.colorWipe(color);

        int delta = 20;

        while (true) {


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int nbright = np.getBrightness() + delta;
            if(nbright <= 0) {
                nbright =0;
                delta = - delta;
                color = swapcolor_adv(color);
                np.colorWipe(color);
                System.out.println("color swapped");
            } else if (nbright >= 255){
                nbright = 255;
                delta = -delta;
            }
            System.out.println("Brightness at " + nbright);
            np.setBrightness(nbright);
            np.render();
        }
    }

    private static NeoPixelColor swapcolor(NeoPixelColor color) {
        NeoPixelColor nc = NeoPixelColor.fromValue(0);
        if (color.equals(NeoPixelColor.GREEN)) nc = NeoPixelColor.RED;
        else if (color.equals(NeoPixelColor.RED)) nc = NeoPixelColor.BLUE;
        else if (color.equals(NeoPixelColor.BLUE)) nc = NeoPixelColor.GREEN;
        return nc;
    }

    private static int num =0;

    private static NeoPixelColor swapcolor_adv(NeoPixelColor color) {
        ++num;
        byte red = (byte) ((num % 2 == 0) ? 255:0);
        byte green = (byte) ((num % 3 == 0 ) ? 255:0);
        byte blue= (byte) ((num % 4 ==0 ) ? 255 : 0);
        if(num>=100) num=0;
        System.out.println("new color: " + red + ", " + green + ", " + blue);
        NeoPixelColor nc = NeoPixelColor.fromBytes(red,green,blue);
        return nc;
    }
}
