/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riftware.demo.helloworld;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;
import com.pi4j.wiringpi.GpioUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author andy
 */
public class ControlPin {
    // create gpio controller instance

    
public static void main(String[] args){
    GpioUtil.enableNonPrivilegedAccess();
    final GpioController gpio = GpioFactory.getInstance();
//    GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,   // PIN NUMBER
//            "My LED", // alias for PIN
//            PinState.LOW);
//     System.out.println(myLed.isHigh());
//    myLed.high();
//    System.out.println(myLed.isHigh());
    GpioPinDigitalOutput[] myPins = new GpioPinDigitalOutput[8];
    myPins[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Relay 1", PinState.HIGH);
    myPins[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Relay 2", PinState.HIGH);
    myPins[2] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "Relay 3", PinState.HIGH);
    myPins[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "Relay 4", PinState.HIGH);
    myPins[4] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Relay 5", PinState.HIGH);
    myPins[5] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Relay 6", PinState.HIGH);
    myPins[6] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "Relay 7", PinState.HIGH);
    myPins[7] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "Relay 8", PinState.HIGH);   
    
  //  for(GpioPinDigitalOutput p:myPins){
    for(int i = 0; i < 64 ; i++){
        
        try {
            System.out.println(i);
            GpioPinDigitalOutput p = myPins[i%8];
            p.low();
            Thread.sleep(500);
            p.high();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlPin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}       

}
