package BabySitterChargingApp;

//  bedTime "inherits" the shiftTime class
public class BedTime extends ShiftTime {

    String timeNeeded = "bed time";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}

