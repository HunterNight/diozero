package com.diozero.sampleapps;

/*
 * #%L
 * Device I/O Zero - Core
 * %%
 * Copyright (C) 2016 diozero
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

import org.pmw.tinylog.Logger;

import com.diozero.LDR;
import com.diozero.util.RuntimeIOException;
import com.diozero.util.SleepUtil;

/**
 * Sample application to illustrate listening for changes to analog values. To run:
 * <ul>
 * <li>sysfs:<br>
 *  {@code java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * <li>JDK Device I/O 1.0:<br>
 *  {@code sudo java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar:diozero-provider-jdkdio10-$DIOZERO_VERSION.jar:dio-1.0.1-dev-linux-armv6hf.jar -Djava.library.path=. com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * <li>JDK Device I/O 1.1:<br>
 *  {@code sudo java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar:diozero-provider-jdkdio11-$DIOZERO_VERSION.jar:dio-1.1-dev-linux-armv6hf.jar -Djava.library.path=. com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * <li>Pi4j:<br>
 *  {@code sudo java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar:diozero-provider-pi4j-$DIOZERO_VERSION.jar:pi4j-core-1.1-SNAPSHOT.jar com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * <li>wiringPi:<br>
 *  {@code sudo java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar:diozero-provider-wiringpi-$DIOZERO_VERSION.jar:pi4j-core-1.1-SNAPSHOT.jar com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * <li>pigpgioJ:<br>
 *  {@code sudo java -cp tinylog-1.1.jar:diozero-core-$DIOZERO_VERSION.jar:diozero-provider-pigpio-$DIOZERO_VERSION.jar:pigpioj-java-1.0.1.jar com.diozero.sampleapps.AnalogListenerTest 0}</li>
 * </ul>
 */
public class LDRListenerTest {
	public static void main(String[] args) {
		if (args.length < 1) {
			Logger.error("Usage: {} <adc-number>", LDRListenerTest.class.getName());
			System.exit(2);
		}

		int adc_number = Integer.parseInt(args[0]);
		float vref = 1.8f;
		int r1 = 10_000;
		test(adc_number, vref, r1);
	}

	public static void test(int adcNumber, float vRef, int r1) {
		try (LDR ldr = new LDR(adcNumber, vRef, r1)) {
			ldr.addListener((event) -> Logger.info("valueChanged({})", event));
			for (int i = 0; i < 10; i++) {
				Logger.info("Scaled: {}, Unscaled: {}", Float.valueOf(ldr.getScaledValue()),
						Float.valueOf(ldr.getUnscaledValue()));
				SleepUtil.sleepSeconds(1);
			}
		} catch (RuntimeIOException e) {
			Logger.error(e, "Error: {}", e);
		}
	}
}