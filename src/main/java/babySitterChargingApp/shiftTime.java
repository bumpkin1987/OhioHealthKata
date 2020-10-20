import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

abstract class shiftTime {
    // I chose abstract class over interface because
    // I needed to dictate common functionality in
    // both startTime and endTime classes.

    public String timeRecieved;
    private String pattern = "h:mm a"; 	
    Date time = new Date();
	Scanner userInput = new Scanner(System.in); 

    // public abstract getHour

    // Function is to take in user input and force them to use the 
	// pattern convention I have laid out and return a Date obj to 
	// establish start of shift, end of shift, and what if at all 
	// when the child(s) went to bed.
	public String getUserShiftInfo(String timeNeeded) {		

        System.out.println("Enter " + timeNeeded + " time: "); 
        timeRecieved = userInput.nextLine();
     
		return timeRecieved;
    }

    // due to this being a kata that has no realworld application 
    // and is for demonstration purposes, I elected to use the 
    // deprecated functions seen below instead of calendar, so
    // I could keep my use of parsing objects to a minimal.
    public Date getShiftTime(String timeInput) {
        //force proper formatting of user input
        boolean inputIsValid = inputValidator(timeInput);
        if (inputIsValid) {
            try {
                time = new SimpleDateFormat(pattern).parse(timeInput);
                int minutes = time.getMinutes();
                
                //need to round to nearest hour which it stored in time object as military time
                if (minutes >= 30) {
                    time.setHours(time.getHours() + 1);
                        
                }
                time.setMinutes(0);

            } catch (Exception e) {
                System.out.println("You must use the format of hh:mm and indicate am/pm");
                shiftGenerator shift = new shiftGenerator();
		        shift.generateShift();
            }
        }
        return time;
    }

    // Tried forever to get pattern, matcher and regex to work to no avail. `\☻/`
    private boolean inputValidator(String timeInput) {
        char[] chars = timeInput.toCharArray();
        // input no matter what has to be at least 6 chars long (5:00pm)but no longer than 8 (05:00 pm )
        if (chars.length >= 6 && Character.isDigit(chars[0]) && !Character.isDigit(chars[4])) {
            if (chars.toString().contains(":")) {
                return true;
            }
        }
        return false;
    }
}