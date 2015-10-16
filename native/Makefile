JNI_INCLUDE = "/usr/lib/jvm/jdk-8-oracle-arm-vfp-hflt/include/"

all: java librpi_ws281x.so demo

librpi_ws281x.so: ws2811.o dma.o pwm.o rpi_ws281x_wrap.o
	${CC} -shared -o $@ $^

java: python/rpi_ws281x.i
	mkdir -p java/com/github/h0ru5/neopixel
	swig -java -package com.github.h0ru5.neopixel -outdir java/com/github/h0ru5/neopixel $<

rpi_ws281x_wrap.o: python/rpi_ws281x_wrap.c
	${CC} -fPIC -I${JNI_INCLUDE} -I${JNI_INCLUDE}/linux -c $<  -o $@

%.o: %.c
	${CC} -fPIC -c $< -o $@

demo: main.c librpi_ws281x.so
	${CC} $? -L. -lrpi_ws281x -Wl,-rpath='$ORIGIN' -o $@
