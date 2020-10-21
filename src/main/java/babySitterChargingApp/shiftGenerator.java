package BabySitterChargingApp;

import java.util.ArrayList;
import java.util.List;


public class ShiftGenerator {

    String startingTime, sleepyTime, endingTime;
    int startingHour, bedtimeHour, endingHour;
    int nightlyCharge = 0;

    private static final int MIDNIGHT = 24;
    private static final int NORMAL_PAYRATE = 12;
    private static final int BEDTIME_PAYRATE = 8;
    private static final int AFTER_MIDNIGHT_PAYRATE = 16;


    public void generateShift() {
        getStartTime();
        getBedTime();
        getEndTime();
        if (timeInputsInChronologicalOrder(startingHour, bedtimeHour, endingHour)) {
            generateNightlyCharge(startingHour, bedtimeHour, endingHour);
            calculateChargeAndDisplay(nightlyCharge);
        }
        else {
            System.out.println("You must put the times in order.");
        }
    }

    public int getStartTime() {
        StartTime startTime = new StartTime();
        startingTime = startTime.getUserShiftInfo();
        startingHour = startTime.generateHourInteger(startingTime);
        startingHour = convertHoursPastMidnight(startingHour);
        return startingHour;
    }

    public int getBedTime() {
        BedTime bedTime = new BedTime();
        sleepyTime = bedTime.getUserShiftInfo();
        bedtimeHour = bedTime.generateHourInteger(sleepyTime);
        bedtimeHour = convertHoursPastMidnight(bedtimeHour);
        return bedtimeHour;
    }

    public int getEndTime() {
        EndTime endTime = new EndTime();
        endingTime = endTime.getUserShiftInfo();
        endingHour = endTime.generateHourInteger(endingTime);
        endingHour = convertHoursPastMidnight(endingHour);
        return endingHour;
    }

    private boolean timeInputsInChronologicalOrder(int shiftStart, int shiftBed, int shiftEnd) {
        if (shiftStart == shiftEnd) {
            return true;
        }
        else if (shiftStart < shiftEnd) {
            if (shiftStart <= shiftBed) {
                if (shiftBed <= shiftEnd) {
                    return true;
                }
            }
        }   
        return false;
    }

    public int generateNightlyCharge(int shiftStart, int shiftBed, int shiftEnd) {

        int beginning = Math.min(shiftStart, MIDNIGHT);

        if (beginning == 24) {
            return (shiftEnd - shiftStart) * AFTER_MIDNIGHT_PAYRATE;
        } 
        else {
            int afterMidnightRate = 0;
            int numHoursBeforeBed = Math.min(shiftBed, MIDNIGHT); // grab which ever value is less
            int numHoursInBedBeforeMidnight = Math.min(MIDNIGHT, shiftEnd); // 
            int beforeBedRate = (numHoursBeforeBed - shiftStart) * NORMAL_PAYRATE;
            int afterBedRate = (numHoursInBedBeforeMidnight - numHoursBeforeBed) * BEDTIME_PAYRATE;

            if (shiftEnd >= 24) {
                afterMidnightRate = (shiftEnd - MIDNIGHT) * AFTER_MIDNIGHT_PAYRATE;
            }
            nightlyCharge =  beforeBedRate + afterBedRate + afterMidnightRate;
        }

        return nightlyCharge;
    }

      // need to convert any hours past midnight for easy calculation of lateHours pay
      // whatever morning hour is procesed midnight through 4:00 am we just add 24
      public int convertHoursPastMidnight(int hourNum) {
        if (hourNum >= 0 && hourNum <= 4) {
            hourNum += 24;
        }
        return hourNum;
    }

    public void calculateChargeAndDisplay(int  nightlyCharge) {

        if (nightlyCharge == 0) {
            System.out.println("You may have entered times out of order, or you didn't work...");
        }
        System.out.println("Charge fee is " + nightlyCharge + "$ dollars.");
    }  
}
