package BabySitterChargingApp;

//  startTime "inherits" the shiftTime class
public class StartTime extends ShiftTime {

    String timeNeeded = "start";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}

