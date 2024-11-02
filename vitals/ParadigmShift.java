package vitals;
public class ParadigmShift {

    static boolean checkTemperature(float temperature) {
        return temperature < 0 || temperature > 45;
    }

    static boolean checkState(float soc) {
        return soc < 20 || soc > 80;
    }

    static boolean checkChargeRate(float chargeRate) {
        return chargeRate > 0.8;
    }

    static String batteryIsOk(float temperature, float soc, float chargeRate, boolean enableTemperatureWarning, boolean enableSocWarning, boolean enableChargeRateWarning) {
        boolean isTemperatureOk = !checkTemperature(temperature);
        boolean isSocOk = !checkState(soc);
        boolean isChargeRateOk = !checkChargeRate(chargeRate);

        return displayParameterStatus("Temperature", isTemperatureOk, temperature, 0, 45, enableTemperatureWarning) +
                displayParameterStatus("State of Charge", isSocOk, soc, 20, 80, enableSocWarning) +
                displayParameterStatus("Charge Rate", isChargeRateOk, chargeRate, 0, 0.8f, enableChargeRateWarning);
    }

    static String displayParameterStatus(String parameterName, boolean isParameterOk, float value, float lowerLimit, float upperLimit, boolean enableWarning) {
        float tolerance = 0.05f * upperLimit;
        if (!isParameterOk) {
            return parameterName + " is out of range!";
        } else if (enableWarning && (value < lowerLimit + tolerance || value > upperLimit - tolerance)) {
            if (value < lowerLimit + tolerance) {
                return "Warning: Approaching discharge for " + parameterName;
            } else if (value > upperLimit - tolerance) {
                return "Warning: Approaching charge-peak for " + parameterName;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        assert (batteryIsOk(25, 70, 0.7f, true, true, true).equals("Warning: Approaching discharge for Charge Rate"));
        assert (batteryIsOk(50, 85, 0.0f, true, true, true).equals("Temperature is out of range!State of Charge is out of range!Warning: Approaching discharge for Charge Rate"));
        assert (batteryIsOk(-1, 85, 0.0f, true, true, true).equals("Warning: Approaching charge-peak for Charge Rate"));
        assert (batteryIsOk(25, 10, 0.0f, true, true, true).equals("Warning: Approaching discharge for Temperature"));
        assert (batteryIsOk(25, 10, 0.0f, true, true, true).equals("State of Charge is out of range!"));
        assert (batteryIsOk(25, 10, 0.0f, false, false, false).equals(""));
    }
}
