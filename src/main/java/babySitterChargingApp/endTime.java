package BabySitterChargingApp;

//  endTime "inherits" the shiftTime class
public class EndTime extends ShiftTime {

    String timeNeeded = "end";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}
    
    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}