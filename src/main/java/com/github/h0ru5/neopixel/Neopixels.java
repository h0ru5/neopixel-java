package com.github.h0ru5.neopixel;

public class Neopixels {

    private final ws2811_channel_t channel;
    private final ws2811_t leds = new ws2811_t();

    private int brightness;

    public Neopixels(int number, int pin, int freq_hz, int dma, boolean invert, int brightness) {
        leds.setDmanum(dma);
        leds.setFreq(freq_hz);

        boolean m_invert = invert;
        this.brightness = brightness;

        nullify();
        this.channel = rpi_ws281x.ws2811_channel_get(leds, 0);
        this.channel.setBrightness(brightness);
        this.channel.setGpionum(pin);
        this.channel.setCount(number);
        this.channel.setInvert(invert ? 1 : 0);
    }

    public static Neopixels createWithDefaults(int number) {
        return new NeopixelsBuilder().setNumber(number).createNeopixels();
    }

    public static NeopixelsBuilder build() {
        return new NeopixelsBuilder();
    }

    public static void main(String[] args) {
        System.loadLibrary("rpi_ws281x");

        Neopixels np = Neopixels.createWithDefaults(64);
        np.init();
        np.render();

        boolean swapped = false;

        while (true) {

            for (int i = 0; i < 64; i++) {
                np.setColor(i, swapped ? NeoPixelColor.RED : NeoPixelColor.GREEN);
		System.out.println("setting " + i + " to "  +  ((swapped)? "red" : "green"));
                np.render();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            swapped = !swapped;
        }
    }

    private void nullify() {
        for (int i = 0; i < 2; i++) {
            ws2811_channel_t channel = rpi_ws281x.ws2811_channel_get(leds, i);
            channel.setBrightness(0);
            channel.setCount(0);
            channel.setInvert(0);
            channel.setGpionum(0);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (leds != null) {
            rpi_ws281x.ws2811_fini(leds);
            leds.delete();
        }
        super.finalize();
    }

    public void init() {
        final int retval = rpi_ws281x.ws2811_init(leds);
        if (retval != 0)
            throw new RuntimeException("Neopixels initialisation exited with error " + retval);
    }

    public void render() {
        final int retval = rpi_ws281x.ws2811_render(leds);
        if (retval != 0)
            throw new RuntimeException("Neopixels rendering exited with error " + retval);
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        this.channel.setBrightness(brightness);
    }

    public void setColor(int num, NeoPixelColor color) {
        rpi_ws281x.ws2811_led_set(channel, num, color.getValue());
    }

    public void setColor(int num, byte red, byte green, byte blue) {
        rpi_ws281x.ws2811_led_set(channel, num, NeoPixelColor.bytesToValue(red, green, blue));
    }

    public NeoPixelColor getColor(int num) {
        return NeoPixelColor.fromValue(rpi_ws281x.ws2811_led_get(channel, num));
    }

}
