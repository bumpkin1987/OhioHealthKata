package BabySitterChargingApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ShiftTime {
    // I chose abstract class because
    // I needed to dictate common functionality in
    // both startTime and endTime classes without instantiating this class obj

    public String timeRecieved;
    private String pattern = "h:mm a";
    private Date time;
    private Scanner userInput;


    public ShiftTime() {
        this.time = new Date();
        this.userInput = new Scanner(System.in);
    }

    // due to this being a kata that has no realworld application 
    // and is for demonstration purposes, I elected to use the 
    // deprecated functions seen below instead of calendar, so
    // I could keep my use of parsing objects to a minimal.

    // Function is to take in user input and force them to use the 
	// pattern convention I have laid out and return a Date obj to 
	// establish start of shift, end of shift, and what if at all 
	// when the child(s) went to bed.
	public String getUserShiftInfo(String timeInput) {	
   
        System.out.println("Enter " + timeInput + " time: "); 
        timeRecieved = userInput.nextLine();
     
        String regex = "\\d?\\d:\\d\\d\\s.([.mM])";
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(timeRecieved);
        boolean inputIsValid = matcher.matches();
        // boolean inputIsValid = inputValidator(timeRecieved);
        if (inputIsValid && timeInputIsInShiftWindow(timeRecieved)) {
            try {
                time = new SimpleDateFormat(pattern).parse(timeRecieved);
                int minutes = time.getMinutes();
                
                //need to round to nearest hour which it stored in time object as military time
                if (minutes >= 30) {
                    time.setHours(time.getHours() + 1);
                    timeRecieved = time.toString();
                }
                time.setMinutes(0);
                timeRecieved = time.toString();                   
            } catch (Exception e) {
                System.out.println("You must enter a time within the shift window 5:00 pm through 4:00 am.");
                ShiftGenerator shift = new ShiftGenerator();
                shift.generateShift();
            }
        } 
        else {
            // I call the shiftGenerator obj to psuedo restart the app quick and dirty
            System.out.println("Please try again, this time enter input in format of hh:mm and indicate am or pm");
            ShiftGenerator shift = new ShiftGenerator();
            shift.generateShift();
        }
        return timeRecieved;
    }    


    // this function is to check and make sure the time given is in the possible shift window
    public boolean timeInputIsInShiftWindow(String timeGiven) {
        String regexMorningHours = "([0-4])";
        String regexAfterNoonHours = "[5-9]|10|11";
        String timeGivenHour = timeGiven.substring(0, 2);
        String amOrPm = timeGiven.substring(timeGiven.length() -2).toLowerCase();

        if (timeGivenHour.endsWith(":")) {
            timeGivenHour = timeGivenHour.substring(0, 1);
        }
        // **accounts for 12:00 am entries**
        if ((amOrPm.equals("am") && timeGivenHour.matches(regexMorningHours)) 
            || (amOrPm.equals("am") && timeGivenHour.equals("12"))) {
            return true;
        }
        else if (amOrPm.equals("pm") && timeGivenHour.matches(regexAfterNoonHours)) {
            return true;
        }
        return false;
    }
    
    // function to parse time from date string return number of military hour
	public int generateHourInteger(String time) {
        int hourNum;
        hourNum =  Integer.parseInt(time.substring(11, 13));
        return hourNum;
	}
}



