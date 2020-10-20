//  endTime "inherits" the shiftTime class
public class endTime extends shiftTime {

    String timeNeeded = "end";

    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}
    
    public int generateHourInteger(String time) {
		return super.generateHourInteger(time);
	}
}