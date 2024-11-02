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
        String parameterStatus = "";
        if (!isParameterOk) {
          parameterStatus= parameterName + " is out of range!";
        } else if (isParameterInWarningLevel(value, lowerLimit, upperLimit, enableWarning, tolerance)) {
          parameterStatus = getWarningMessage(parameterName, value, lowerLimit, upperLimit, tolerance);
        }
        return parameterStatus;
    }

    /**
     * @param value
     * @param lowerLimit
     * @param upperLimit
     * @param enableWarning
     * @param tolerance
     * @return
     */
    private static boolean isParameterInWarningLevel(float value, float lowerLimit, float upperLimit, boolean enableWarning,
        float tolerance) {
      return enableWarning && (value < lowerLimit + tolerance || value > upperLimit - tolerance);
    }

    
     /**
     * @param parameterName
     * @param value
     * @param lowerLimit
     * @param upperLimit
     * @param tolerance
     */
    private static String getWarningMessage(String parameterName, float value, float lowerLimit, float upperLimit,
        float tolerance) {
      String message = "";
      if (value < lowerLimit + tolerance) {
          message = "Warning: Approaching discharge for " + parameterName;
      } else if (value > upperLimit - tolerance) {
          message = "Warning: Approaching charge-peak for " + parameterName;
      }
      return message;
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
