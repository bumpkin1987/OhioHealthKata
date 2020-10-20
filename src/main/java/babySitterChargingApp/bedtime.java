package babySitterChargingApp;

//  bedTime "inherits" the shiftTime class
public class bedTime extends shiftTime {

    String timeNeeded = "bed time";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}

