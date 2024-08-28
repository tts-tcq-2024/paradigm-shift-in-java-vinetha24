package vitals;

static boolean checkTemperature(float temperature){
    return temperature < 0 || temperature > 45;
}

static boolean checkState(float soc){
    return soc < 20 || soc > 80;
}

static boolean checkChargeRate(float chargeRate){
    return chargeRate > 0.8;
}

public class Main {
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        if(checkTemperature(temperature)) {
            System.out.println("Temperature is out of range!");
            return false;
        } else if(checkState(soc)) {
            System.out.println("State of Charge is out of range!");
            return false;
        } else if(checkChargeRatechargeRate()) {
            System.out.println("Charge Rate is out of range!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert(batteryIsOk(25, 70, 0.7f) == true);
        assert(batteryIsOk(50, 85, 0.0f) == false);
        assert(batteryIsOk(50, 85, 0.0f) == false);
        assert(batteryIsOk(-1, 85, 0.0f) == false);
        assert(batteryIsOk(25, 10, 0.0f) == false);
        assert(batteryIsOk(25, 85, 0.0f) == false);
         assert(batteryIsOk(25, 25, 0.9f) == false);
         assert(batteryIsOk(25, 85, 0.0f) == false);
        
    }
}
