JNI_INCLUDE = "/usr/lib/jvm/default/include"

all: java rpi_ws281x.so

rpi_ws281x.so: ws2811.o dma.o pwm.o rpi_ws281x_wrap.o
	${CC} -shared -o $@ $^

java: python/rpi_ws281x.i
	mkdir -p java
	swig -java -outdir java $<

rpi_ws281x_wrap.o: python/rpi_ws281x_wrap.c
	${CC} -fPIC -I${JNI_INCLUDE} -I${JNI_INCLUDE}/linux -c $<  -o $@

%.o: %.c
	${CC} -fPIC -c $< -o $@
