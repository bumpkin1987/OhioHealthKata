package BabySitterChargingApp;

import java.util.ArrayList;
import java.util.LinkedList;

public class ShiftGenerator {

    String startingTime, sleepyTime, endingTime;
    int startingHour, bedtimeHour, endingHour;
    int nightlyCharge = 0;
    LinkedList<Hour> shiftWindow = new LinkedList<Hour>();

    public void generateShift() {
        getStartTime();
        getBedTime();
        getEndTime();
        generateShiftWindow(startingHour, bedtimeHour, endingHour);
        calculateChargeAndDisplay(shiftWindow);
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

    public LinkedList<Hour> generateShiftWindow(int shiftStart, int shiftBed, int shiftEnd) {
        
        ArrayList<Integer> shiftHours = new ArrayList<Integer>();
        shiftHours.add(shiftStart);
        int firstHour = shiftHours.get(0);

        // if bed time is midnight or later we want to change the value of bedtime to >= 24
        if (shiftBed <= 24 && firstHour < shiftBed) {
            if (firstHour <= 23 && firstHour >= 17) { //  in case clock in is after midnight for some reason
                // create hour objects for normal pay
                // add normal hour objs to linked list
                for(int i = firstHour; i < shiftBed; i++) {
                    Hour normalPayHour = new Hour(i, 12, false);
                    shiftWindow.add(normalPayHour);
                }
            }
        }
        if (shiftBed <= 23 && shiftBed > firstHour && shiftBed <= shiftEnd) { // making sure if child is already in bed then all hours before 12 are billed at 8$/hr
            // create bedtime pay hours
            // add bedtime hours to linked list after normal hours
            for(int i = shiftBed; i < shiftEnd; i++) {
                Hour bedTimePayHour = new Hour(i, 8, true);
                shiftWindow.add(bedTimePayHour);
            }
        }
        if (shiftEnd <= 28 && shiftEnd > 24) {
            // create late night pay hours
            // add late night hours to linked list after bedtime hours
            // need to iterate backwards due to disparity in hour numbers given past midnight
            for(int i = shiftEnd; i >= 25; i--) {
                Hour endTimePayHour = new Hour(i, 16, true);
                shiftWindow.add(endTimePayHour);
            }
        }

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
    public void calculateChargeAndDisplay(LinkedList<Hour>  shiftWindow) {

        for (Hour hour : shiftWindow) {
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
