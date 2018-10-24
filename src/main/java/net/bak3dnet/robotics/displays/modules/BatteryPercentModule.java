package net.bak3dnet.robotics.displays.modules;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.wpi.first.wpilibj.RobotController;

import net.bak3dnet.robotics.displays.RevDigitDisplay;

/**
 * A module dedicated to showing the current battery percentage of the battery.
 * 
 * @author Jake Armstrong
 * @version 0.1.0
 * @since 0.1.0
 */
public class BatteryPercentModule implements DisplayModuleBase {

    private static final NumberFormat subHundo;
    private static final NumberFormat supHundo;

    private double emptyVoltage;

    static {

        subHundo = new DecimalFormat("#.##");
        supHundo = new DecimalFormat("#.#");

    }

    /**
     * Constructs the BatteryPercentModule with the percentage at which the battery is at 0%.
     * 
     * @param minVoltage The voltage for when the battery is at 0%
     */
    public BatteryPercentModule(double minVoltage) {

        emptyVoltage = minVoltage;

    }

    @Override
    public void task(RevDigitDisplay display, double deltaTime) {

        display.setText(getFormattedPercentage());

    }

    /**
     * Gets the percentage of the battery.
     * 
     * @return The current percentage of the battery.
     */
    private double getPercentage() {
        
        return (RobotController.getBatteryVoltage()-emptyVoltage)*100D;

    }

    private String getFormattedPercentage() {

        double percentage = getPercentage();
        String formattedDecimal;

        if(percentage > 100) {

            formattedDecimal = supHundo.format(percentage);

        } else {

            formattedDecimal = subHundo.format(percentage);

        }

        return formattedDecimal;

    }

    public String toString() {

        return "Battery Percentage";

    }

    @Override
    public void close() {

    }

}