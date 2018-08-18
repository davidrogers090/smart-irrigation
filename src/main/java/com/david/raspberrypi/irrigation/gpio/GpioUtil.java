package com.david.raspberrypi.irrigation.gpio;


import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class GpioUtil {

	private static final GpioController GPIO = GpioFactory.getInstance();
	private static final GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[RaspiPin.allPins().length];
	public static final int MAX_ZONES = pins.length;
	
	private GpioUtil(){}
	
	private static GpioPinDigitalOutput getPin(int pin){
		
		// provision pin if it isn't already
		if (pins[pin] == null){
			pins[pin] = GPIO.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pin),PinState.LOW);
		}
		
		return pins[pin];
	}
	
	public static void activate(int pin){
		getPin(pin).high();
	}
	
	public static void deactivate(int pin){
		getPin(pin).low();
	}
	
}
