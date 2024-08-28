package vitals;

public class Main {

static boolean checkTemperature(float temperature){
    return temperature < 0 || temperature > 45;
}

static boolean checkState(float soc){
    return soc < 20 || soc > 80;
}

static boolean checkChargeRate(float chargeRate){
    return chargeRate > 0.8;
}
   static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
     boolean isTemperatureOk = !checkTemperature(temperature);
     boolean isSocOk = !checkState(soc);
     boolean isChargeRateOk = !checkChargeRate(chargeRate);
     display(isTemperatureOk,isSocOk,isChargeRateOK)
     

     return isTemperatureOk && isSocOk && isChargeRateOk;
 }

    static void display(boolean isTemperatureOk,boolean isSocOk,boolean isChargeRateOK){
    if (!isTemperatureOk) {
         System.out.println("Temperature is out of range!");
     }
     if (!isSocOk) {
         System.out.println("State of Charge is out of range!");
     }
     if (!isChargeRateOk) {
         System.out.println("Charge Rate is out of range!");
     }
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
