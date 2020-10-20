package babySitterChargingApp;

// I chouse to go with an hour class due to the
// requirements wanting to only bill in whole hour increments. 
// Each hour will have the necessary information on what 
// is needed to calculae the shift charge fee. 
// I would even take this further and may child classes 
// representing the types of hours basd on payrate and
// whether the child is in bed. this allows for fute planning of any
// other factors which a babysitter could augment such as help in schoolwork,
// for instance if a babsitter would charge 20 $/hr during the hours they help
// with school work.
public class hour {

    int hour, payRate;
 boolean inBed;


 public hour(int hour, int payRate, boolean inBed) {
     this.hour = hour;
     this.payRate = payRate;
     this.inBed = inBed;
 }
 
 // normal hours pay rate is 12$/hr
 // bedtime hours before midnight is 8$/hr
 // late hours (after midnight) are 16$/hr
 public int getHourlyPayRate() {
 
     if (hour <= 28 && hour > 24) {
         payRate = 16;
     }
     else if (hour >= 17 && hour < 24 && inBed == true) {
         payRate = 8;
     }
     else {
         payRate = 12;
     }
     return payRate;
 }
}
