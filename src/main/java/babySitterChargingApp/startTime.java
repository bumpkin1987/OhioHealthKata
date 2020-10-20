package babySitterChargingApp;

//  startTime "inherits" the shiftTime class
public class startTime extends shiftTime {

    String timeNeeded = "start";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}

