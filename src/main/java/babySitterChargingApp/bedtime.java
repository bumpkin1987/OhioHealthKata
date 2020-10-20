import java.util.Date;

//  startTime "implements" the shiftTime interface
public class bedTime extends shiftTime {

    String timeNeeded = "bed time";

    // overloading
    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    @Override
	public Date getShiftTime(String timeInput) {
        return super.getShiftTime(timeInput);
	}
}

