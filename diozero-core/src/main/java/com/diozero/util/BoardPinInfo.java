package com.diozero.util;

/*
 * #%L
 * Device I/O Zero - Core
 * %%
 * Copyright (C) 2016 - 2017 mattjlewis
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
import java.util.*;

import com.diozero.api.DeviceMode;
import com.diozero.api.PinInfo;
import com.diozero.api.PwmPinInfo;

public class BoardPinInfo {
	private static final String GPIO_KEY_PREFIX = "GPIO";
	private static final String DEFAULT_GPIO_NAME_PREFIX = GPIO_KEY_PREFIX;
	private static final String ADC_KEY_PREFIX = "AIN";
	private static final String DEFAULT_ADC_NAME_PREFIX = ADC_KEY_PREFIX;
	private static final String DAC_KEY_PREFIX = "AOUT";
	private static final String DEFAULT_DAC_NAME_PREFIX = DAC_KEY_PREFIX;
	
	private Map<Integer, PinInfo> gpios;
	private Map<Integer, Integer> pwmNumToGpioMapping;
	private Map<Integer, PinInfo> adcs;
	private Map<Integer, PinInfo> dacs;
	private Map<String, PinInfo> pinsByName;
	private Map<Integer, PinInfo> pinsByNumber;
	
	public BoardPinInfo() {
		gpios = new HashMap<>();
		pwmNumToGpioMapping = new HashMap<>();
		adcs = new HashMap<>();
		dacs = new HashMap<>();
		pinsByName = new HashMap<>();
		pinsByNumber = new TreeMap<>();
	}
	
	protected PinInfo addGpioPinInfo(int gpioNum, int pin, EnumSet<DeviceMode> modes) {
		return addGpioPinInfo(PinInfo.DEFAULT_HEADER, gpioNum, DEFAULT_GPIO_NAME_PREFIX + gpioNum, pin, modes);
	}
	
	protected PinInfo addGpioPinInfo(int gpioNum, String name, int pin, EnumSet<DeviceMode> modes) {
		return addGpioPinInfo(PinInfo.DEFAULT_HEADER, gpioNum, name, pin, modes);
	}
	
	protected PinInfo addGpioPinInfo(String header, int gpioNum, int pin, EnumSet<DeviceMode> modes) {
		return addGpioPinInfo(header, gpioNum, DEFAULT_GPIO_NAME_PREFIX + gpioNum, pin, modes);
	}
	
	protected PinInfo addGpioPinInfo(String header, int gpioNum, String name, int pin, EnumSet<DeviceMode> modes) {
		PinInfo pin_info = new PinInfo(GPIO_KEY_PREFIX, header, gpioNum, pin, name, modes);
		addGpioPinInfo(pin_info);
		return pin_info;
	}
	
	protected void addGpioPinInfo(PinInfo pinInfo) {
		gpios.put(Integer.valueOf(pinInfo.getDeviceNumber()), pinInfo);
		pinsByName.put(pinInfo.getName(), pinInfo);
		if (pinInfo.getPinNumber() != PinInfo.NOT_DEFINED) {
			pinsByNumber.put(Integer.valueOf(pinInfo.getPinNumber()), pinInfo);
		}
	}
	
	protected PinInfo addPwmPinInfo(int gpioNum, int pin, int pwmNum, EnumSet<DeviceMode> modes) {
		return addPwmPinInfo(PinInfo.DEFAULT_HEADER, gpioNum, DEFAULT_GPIO_NAME_PREFIX + gpioNum, pin, pwmNum, modes);
	}
	
	protected PinInfo addPwmPinInfo(int gpioNum, String name, int pin, int pwmNum, EnumSet<DeviceMode> modes) {
		return addPwmPinInfo(PinInfo.DEFAULT_HEADER, gpioNum, name, pin, pwmNum, modes);
	}
	
	protected PinInfo addPwmPinInfo(String header, int gpioNumber, String name, int pin, int pwmNum, EnumSet<DeviceMode> modes) {
		PinInfo pin_info = new PwmPinInfo(GPIO_KEY_PREFIX, header, gpioNumber, pin, pwmNum, name, modes);
		addGpioPinInfo(pin_info);
		pwmNumToGpioMapping.put(Integer.valueOf(pwmNum), Integer.valueOf(gpioNumber));
		return pin_info;
	}
	
	protected PinInfo addAdcPinInfo(int adcNumber, int pin) {
		return addAdcPinInfo(PinInfo.DEFAULT_HEADER, adcNumber, DEFAULT_ADC_NAME_PREFIX + adcNumber, pin);
	}
	
	protected PinInfo addAdcPinInfo(int adcNumber, String name, int pin) {
		return addAdcPinInfo(PinInfo.DEFAULT_HEADER, adcNumber, name, pin);
	}
	
	protected PinInfo addAdcPinInfo(String header, int adcNumber, String name, int pin) {
		PinInfo pin_info = new PinInfo(ADC_KEY_PREFIX, header, adcNumber, pin, name, PinInfo.ANALOG_INPUT);
		addAdcPinInfo(pin_info);
		return pin_info;
	}
	
	protected void addAdcPinInfo(PinInfo pinInfo) {
		adcs.put(Integer.valueOf(pinInfo.getDeviceNumber()), pinInfo);
		pinsByName.put(pinInfo.getName(), pinInfo);
		if (pinInfo.getPinNumber() != PinInfo.NOT_DEFINED) {
			pinsByNumber.put(Integer.valueOf(pinInfo.getPinNumber()), pinInfo);
		}
	}
	
	protected PinInfo addDacPinInfo(int dacNumber, int pin) {
		return addDacPinInfo(PinInfo.DEFAULT_HEADER, dacNumber, DEFAULT_DAC_NAME_PREFIX + dacNumber, pin);
	}
	
	protected PinInfo addDacPinInfo(int dacNumber, String name, int pin) {
		return addDacPinInfo(PinInfo.DEFAULT_HEADER, dacNumber, name, pin);
	}
	
	protected PinInfo addDacPinInfo(String header, int dacNumber, int pin) {
		return addDacPinInfo(PinInfo.DEFAULT_HEADER, dacNumber, DEFAULT_DAC_NAME_PREFIX + dacNumber, pin);
	}
	
	protected PinInfo addDacPinInfo(String header, int dacNumber, String name, int pin) {
		PinInfo pin_info = new PinInfo(DAC_KEY_PREFIX, header, dacNumber, pin, name, PinInfo.ANALOG_OUTPUT);
		addDacPinInfo(pin_info);
		return pin_info;
	}
	
	protected void addDacPinInfo(PinInfo pinInfo) {
		dacs.put(Integer.valueOf(pinInfo.getDeviceNumber()), pinInfo);
		pinsByName.put(pinInfo.getName(), pinInfo);
		if (pinInfo.getPinNumber() != PinInfo.NOT_DEFINED) {
			pinsByNumber.put(Integer.valueOf(pinInfo.getPinNumber()), pinInfo);
		}
	}
	
	public PinInfo getByGpioNumber(int gpio) {
		return gpios.get(Integer.valueOf(gpio));
	}
	
	public PinInfo getByPwmNumber(int pwmNum) {
		Integer gpio = pwmNumToGpioMapping.get(Integer.valueOf(pwmNum));
		if (gpio == null) {
			return null;
		}
		return gpios.get(gpio);
	}
	
	public PinInfo getByAdcNumber(int adcNumber) {
		return adcs.get(Integer.valueOf(adcNumber));
	}
	
	public PinInfo getByDacNumber(int dacNumber) {
		return dacs.get(Integer.valueOf(dacNumber));
	}
	
	public PinInfo getByName(String name) {
		return pinsByName.get(name);
	}
	
	public Collection<PinInfo> getPins() {
		return pinsByNumber.values();
	}
	
	@SuppressWarnings("static-method")
	public int mapToSysFsGpioNumber(int gpio) {
		return gpio;
	}
}
