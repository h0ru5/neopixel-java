package com.github.h0ru5.neopixel;

/**
 * Created by mchn1210 on 19.10.2015.
 */
public class DemoRunner {
    public static void main(String[] args) {
        System.loadLibrary("rpi_ws281x");

        Neopixels np = Neopixels.createWithDefaults(64);
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
                if (color.equals(NeoPixelColor.GREEN)) color = NeoPixelColor.RED;
                else if (color.equals(NeoPixelColor.RED)) color = NeoPixelColor.BLUE;
                else if (color.equals(NeoPixelColor.BLUE)) color = NeoPixelColor.GREEN;
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
}
