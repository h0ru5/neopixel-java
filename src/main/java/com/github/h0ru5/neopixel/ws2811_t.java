package com.github.h0ru5.neopixel;

public class ws2811_t {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ws2811_t(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    public ws2811_t() {
        this(rpi_ws281xJNI.new_ws2811_t(), true);
    }

    protected static long getCPtr(ws2811_t obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                rpi_ws281xJNI.delete_ws2811_t(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public SWIGTYPE_p_ws2811_device getDevice() {
        long cPtr = rpi_ws281xJNI.ws2811_t_device_get(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_ws2811_device(cPtr, false);
    }

    public void setDevice(SWIGTYPE_p_ws2811_device value) {
        rpi_ws281xJNI.ws2811_t_device_set(swigCPtr, this, SWIGTYPE_p_ws2811_device.getCPtr(value));
    }

    public long getFreq() {
        return rpi_ws281xJNI.ws2811_t_freq_get(swigCPtr, this);
    }

    public void setFreq(long value) {
        rpi_ws281xJNI.ws2811_t_freq_set(swigCPtr, this, value);
    }

    public int getDmanum() {
        return rpi_ws281xJNI.ws2811_t_dmanum_get(swigCPtr, this);
    }

    public void setDmanum(int value) {
        rpi_ws281xJNI.ws2811_t_dmanum_set(swigCPtr, this, value);
    }

    public ws2811_channel_t getChannel() {
        long cPtr = rpi_ws281xJNI.ws2811_t_channel_get(swigCPtr, this);
        return (cPtr == 0) ? null : new ws2811_channel_t(cPtr, false);
    }

    public void setChannel(ws2811_channel_t value) {
        rpi_ws281xJNI.ws2811_t_channel_set(swigCPtr, this, ws2811_channel_t.getCPtr(value), value);
    }

}
