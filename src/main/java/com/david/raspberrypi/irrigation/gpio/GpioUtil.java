package com.david.raspberrypi.irrigation.gpio;


import org.jboss.logging.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinProvider;
import com.pi4j.io.gpio.PinState;

/**
 * This class handles the activation of the hardware pins.
 * Currently, the pin to irrigation connections are expected to be
 * one-to-one. i.e. Each pin has a dedicated irrigation zone.
 * This class may be changed in the future to support serial
 * messages. This would allow for more irrigation zones than
 * the hardware has pins.
 * <p>
 * If the hardware libraries cannot be accessed, this class will
 * be disabled. This is useful for testing purposes.
 * @author David
 *
 */
public final class GpioUtil {

	private static final Logger LOGGER = Logger.getLogger(GpioUtil.class);

	private static GpioController GPIO = null;
	private static GpioPinDigitalOutput[] pins = null;
	public static int MAX_ZONES = -1;

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
