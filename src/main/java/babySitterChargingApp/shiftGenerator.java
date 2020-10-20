package babySitterChargingApp;

// import java.util.ArrayList;
import java.util.LinkedList;

public class shiftGenerator {

    String startingTime, sleepyTime, endingTime;
    int startingHour, bedtimeHour, endingHour;
    int nightlyCharge = 0;
    LinkedList<hour> shiftWindow = new LinkedList<hour>();

    public void generateShift() {
        getStartTime();
        getBedTime();
        getEndTime();
        generateShiftWindow(startingHour, bedtimeHour, endingHour);
        calculateChargeAndDisplay(shiftWindow);
    }

    public int getStartTime() {
        startTime startTime = new startTime();
        startingTime = startTime.getUserShiftInfo();
        startingHour = startTime.generateHourInteger(startingTime);
        startingHour = convertHoursPastMidnight(startingHour);
        return startingHour;
    }

    public int getBedTime() {
        bedTime bedTime = new bedTime();
        sleepyTime = bedTime.getUserShiftInfo();
        bedtimeHour = bedTime.generateHourInteger(sleepyTime);
        bedtimeHour = convertHoursPastMidnight(bedtimeHour);
        return bedtimeHour;
    }

    public int getEndTime() {
        endTime endTime = new endTime();
        endingTime = endTime.getUserShiftInfo();
        endingHour = endTime.generateHourInteger(endingTime);
        endingHour = convertHoursPastMidnight(endingHour);
        return endingHour;
    }

    public LinkedList<hour> generateShiftWindow(int shiftStart, int shiftBed, int shiftEnd) {
        // make a list of hours to calculate pay, maybe use a hashmap to iterate through
        return shiftWindow;
    }

      // need to convert any hours past midnight for easy calculation of lateHours pay
      public int convertHoursPastMidnight(int morningHour) {
        if (morningHour == 0) {
            morningHour = 24;
        }
        else if (morningHour > 0 && morningHour <= 4) {
            morningHour = morningHour + 24;
        }
        return morningHour;
    }

    // function to calculate nightly charge. I need an array of hour ojects
    // to add the payrate values stored in each hour of the shift
    public void calculateChargeAndDisplay(LinkedList<hour>  shiftWindow) {

        for (hour hour : shiftWindow) {
            nightlyCharge += hour.getHourlyPayRate();
        }
       
        // iterate over hours for each hour get the payrate
        // add all the rates up to get nightly charge
        if (nightlyCharge == 0) {
            System.out.println("You may have entered times out of order, Try again");
        }
        System.out.println("Charge fee is " + nightlyCharge + "$ dollars.");
    }  
}
