package BabySitterChargingApp;

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
            display(nightlyCharge);
        }
        else {
            System.out.println("You must put the times in order.");
        }
    }

    public int getStartTime() {
        StartTime startTime = new StartTime();
        startingTime = startTime.getUserShiftInfo(); // getting and validating the users time input.
        startingHour = startTime.generateHourInteger(startingTime); // convert to int for pay calculations.
        startingHour = convertHoursPastMidnight(startingHour); // midnight to 4am will be represented as 24 - 28 for easy calculation.
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

    private boolean timeInputsInChronologicalOrder(int shiftStart, int shiftBed, int shiftEnd) { // making sure times uer puts in are in chronological order. -> start <= bedtime <= end.
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

        if (beginning == MIDNIGHT) { // if shift starts at midnight what ever the difference betwen start and end is how many hours to bill at 16$/hr.
            return (shiftEnd - shiftStart) * AFTER_MIDNIGHT_PAYRATE;
        } 
        else { // all scenarios that have hours to be billed at before midniht either 12$/hr or 16$/hr.
            int after_Midnight_Charge_Amount = 0;                     
            int num_Hours_Before_Bed = Math.min(shiftBed, MIDNIGHT); // grab which ever value is less (PRE MIDNIGHT) to calculate hours in bed and normal hours.
            int num_Hours_In_Bed_Before_Midnight = Math.min(MIDNIGHT, shiftEnd); // grab which ever value 
            int normal_Hours_Charge_Amount = (num_Hours_Before_Bed - shiftStart) * NORMAL_PAYRATE;
            int bedtime_Hours_Charge_Amount = (num_Hours_In_Bed_Before_Midnight - num_Hours_Before_Bed) * BEDTIME_PAYRATE;

            if (shiftEnd >= 24) {
                after_Midnight_Charge_Amount = (shiftEnd - MIDNIGHT) * AFTER_MIDNIGHT_PAYRATE;
            }
            nightlyCharge =  normal_Hours_Charge_Amount + bedtime_Hours_Charge_Amount + after_Midnight_Charge_Amount;
        }

        return nightlyCharge;
    }

      // need to convert any hours past midnight for easy calculation of lateHours pay
      // whatever morning hour is procesed midnight through 4:00 am we just add 24
      public int convertHoursPastMidnight(int hourNum) {
        if (hourNum >= 0 && hourNum <= 4) {
            hourNum += MIDNIGHT;
        }
        return hourNum;
    }

    public void display(int  nightlyCharge) {

        if (nightlyCharge == 0) {
            System.out.println("You may have entered times out of order, or you didn't work...");
        }
        System.out.println("Charge fee is " + nightlyCharge + "$ dollars.");
    }  
}
