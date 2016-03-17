package com.github.h0ru5.neopixel;


public class NeopixelsImpl implements Neopixels {
    private final static LibneopixelLibrary rpi_ws281x = LibneopixelLibrary.INSTANCE;
    private final ws2811_t leds = new ws2811_t();
    private ws2811_channel_t channel;

    @Override
    public int getBrightness() {
        return brightness;
    }

    private int brightness;

    public NeopixelsImpl(int number, int pin, int freq_hz, int dma, boolean invert, int brightness) {
        leds.channel[0] = new ws2811_channel_t();
        ws2811_channel_t channel = this.channel = leds.channel[0];
        channel.count = (number);
        channel.invert = (invert ? 1 : 0);
        channel.gpionum = (pin);
        channel.brightness = (brightness);
        channel.write();

        leds.channel[1] = new ws2811_channel_t();
        channel = leds.channel[1];
        channel.count = (0);
        channel.invert = (0);
        channel.gpionum = (0);
        channel.brightness = (0);
        channel.write();

        leds.dmanum = dma;
        leds.freq = freq_hz;
        this.brightness = brightness;

        this.channel = leds.channel[0];
        this.channel.brightness = brightness;
        this.channel.gpionum = pin;
        this.channel.count = number;
        this.channel.invert = invert ? 1 : 0;

        this.init();
    }

    public static Neopixels createWithDefaults(int number) {
        return new NeopixelsBuilder().setNumber(number).createNeopixels();
    }

    public static NeopixelsBuilder build() {
        return new NeopixelsBuilder();
    }

    @Override
    protected void finalize() throws Throwable {
        if (leds != null) {
            rpi_ws281x.ws2811_fini(leds);
        }
        super.finalize();
    }

    @Override
    public void init() {
        final int retval = rpi_ws281x.ws2811_init(leds);
        if (retval != 0)
            throw new RuntimeException("Neopixels initialisation exited with error " + retval);
        this.channel = leds.channel[0];
    }

    @Override
    public void render() {
        final int retval = rpi_ws281x.ws2811_render(leds);
        if (retval != 0)
            throw new RuntimeException("Neopixels rendering exited with error " + retval);
    }

    @Override
    public void setBrightness(int brightness) {
        this.brightness = brightness;
        this.channel.brightness = (brightness);
        this.render();
    }

    @Override
    public void setColor(int num, NeoPixelColor color) {
        channel.leds.setInt(4 * num, (int) color.getValue());
        this.render();
    }

    @Override
    public void setColor(int num, byte red, byte green, byte blue) {
        channel.leds.setInt(4 * num, (int) NeoPixelColor.bytesToValue(red, green, blue));
        this.render();
    }

    @Override
    public void setColor(int num, long uint_32) {
        channel.leds.setInt(4 * num, (int) uint_32);
        this.render();
    }


    @Override
    public void colorWipe(NeoPixelColor color) {
        int value = (int) color.getValue();
        for (int i = 0; i < this.channel.count; i++) {
            channel.leds.setInt(4 * i, value);
        }
        this.render();
    }

    @Override
    public NeoPixelColor getColor(int num) {
        return NeoPixelColor.fromValue(channel.leds.getInt(4 * num));
    }

}
