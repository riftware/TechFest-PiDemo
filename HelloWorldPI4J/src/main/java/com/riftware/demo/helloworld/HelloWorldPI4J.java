/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riftware.demo.helloworld;

import com.pi4j.component.lcd.impl.I2CLcdDisplay;
import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.spi.SpiChannel;
import com.pi4j.system.NetworkInfo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.pi4j.component.temperature.impl.Tmp102;
import com.pi4j.temperature.TemperatureScale;

/**
 *
 * @author andy
 */
public class HelloWorldPI4J {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            final GpioController gpio = GpioFactory.getInstance();
            final AdcGpioProvider provider = new MCP3008GpioProvider(SpiChannel.CS0);
       //     GpioPinAnalogInput input = gpio.provisionAnalogInputPin(provider, MCP3008Pin.CH0, "MyAnalogInput-CH0");
            
            System.out.println("Hello World");
            try {
                    Tmp102 temp = new Tmp102(I2CBus.BUS_1,0x48);
                    I2CLcdDisplay lcd = new I2CLcdDisplay(2, 21,
                          I2CBus.BUS_1, 0x3F, 3, 0, 1, 2, 7, 6, 5, 4);
                    lcd.setCursorHome();
                    lcd.write(NetworkInfo.getHostname() + " "// + getADC(input) + " "
                        + Double.toString(temp.getTemperature(TemperatureScale.FARENHEIT)));
                    lcd.setCursorPosition(1);
                    lcd.write(getInfo());
                while (true) {
                    lcd.setCursorPosition(0, 9);
                    lcd.write(//getADC(input)+ " "
                         Double.toString(temp.getTemperature(TemperatureScale.FARENHEIT)));
                    
//                    lcd.write(NetworkInfo.getHostname() + "   " + getADC(input));
//                    lcd.setCursorPosition(1);
//                    lcd.write(getInfo());
                    Thread.sleep(250);
                }
            } catch (Exception ex) {
                Logger.getLogger(HelloWorldPI4J.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldPI4J.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getInfo() {
        String retString = "127.0.0.1";
        
        try {
            for (String ipAddress : NetworkInfo.getIPAddresses()) {
                System.out.println("IP Addresses      :  " + ipAddress);
                if (!ipAddress.startsWith("127.0")) {
                    retString = ipAddress;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldPI4J.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloWorldPI4J.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retString;
    }
    
    public static String getADC(GpioPinAnalogInput input) {
        String retString = "N/A";        
        final GpioController gpio = GpioFactory.getInstance();
        retString = Double.toString(input.getValue()/10);
        return retString;
        
    }
}
