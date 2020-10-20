import java.util.Date;

//  endTime "implements" the shiftTime interface
public class endTime extends shiftTime {

    String timeNeeded = "end";

    // overloading
    public String getUserShiftInfo() {			
		return super.getUserShiftInfo(timeNeeded);
	}
    
    @Override
    public Date getShiftTime(String timeInput) {
        return super.getShiftTime(timeInput);
    }
}