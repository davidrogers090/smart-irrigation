package com.david.raspberrypi.irrigation.gpio;


import org.jboss.logging.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinProvider;
import com.pi4j.io.gpio.PinState;

public final class GpioUtil {

	private static final Logger LOGGER = Logger.getLogger(GpioUtil.class);

	private static GpioController GPIO = null; // = GpioFactory.getInstance();
	private static GpioPinDigitalOutput[] pins = null; // = new GpioPinDigitalOutput[RaspiPin.allPins().length];
	public static int MAX_ZONES = -1; // = pins.length;

	private static boolean enabled = true;

	static {

		try {
			GPIO = GpioFactory.getInstance();
			pins = new GpioPinDigitalOutput[PinProvider.allPins().length];
			MAX_ZONES = pins.length;
		} catch (IllegalArgumentException|UnsatisfiedLinkError e) {
			LOGGER.error("GPIO is unable to initialize. Disabling GPIO.");
			enabled = false;
		}

	}

	private GpioUtil(){}

	private static GpioPinDigitalOutput getPin(int pin){
		// provision pin if it isn't already
		if (pins[pin] == null){
			pins[pin] = GPIO.provisionDigitalOutputPin(PinProvider.getPinByAddress(pin),PinState.LOW);
		}

		return pins[pin];
	}

	public static void activate(int pin){
		if (enabled) {
			getPin(pin).high();
		}
	}

	public static void deactivate(int pin){
		if (enabled) {
			getPin(pin).low();
		}
	}

}
