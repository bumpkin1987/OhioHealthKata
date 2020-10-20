import java.util.Date;

//  startTime "implements" the shiftTime interface
public class startTime extends shiftTime {

    String timeNeeded = "start";

	// overloading
    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}

    @Override
	public Date getShiftTime(String timeInput) {
        return super.getShiftTime(timeInput);
	}
}

