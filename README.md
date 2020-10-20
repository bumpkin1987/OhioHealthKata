# OhioHealthKata
A small console app that allows a baby sitter to input the times they worked for a night and calculate how much to charge the client.
What you’ll need :
  text editor or IDE
  JDK 6 or later
  Install Gradle


Description of kata
Taken from https://gist.github.com/jameskbride/5482722. (Preservation fork here: https://gist.github.com/jduffey/dd68b22689713ab575d57b2119949f8c)

This kata simulates a babysitter working and getting paid for one night. The rules are pretty straight forward:

The babysitter

starts no earlier than 5:00PM
leaves no later than 4:00AM
gets paid $12/hour from start-time to bedtime
gets paid $8/hour from bedtime to midnight
gets paid $16/hour from midnight to end of job
gets paid for full hours (no fractional hours)
Feature: As a babysitter In order to get paid for 1 night of work I want to calculate my nightly charge

Pre-coding notes
The goal of this exercise is to create a method that will output a single value that is the amount of money owed to a babysitter after the completion of one shift.
The method that we need to create that will output this value will require three inputs:
The starting time of the babysitter's shift.
The ending time of the babysitter's shift.
The bedtime.
There are three different rates at which the babysitter can be paid, and thus we need to break down the problem by finding how many hours are worked under each pay rate. (This is the "real" challenge of the exercise; calculating the amount to be paid is a simple arithmetic problem of multiplication and addition once we have calculated the amount of individual sub-shift hours.)
We can see that any hour worked after midnight has a pay rate that supersedes all other pay rates. We'll call these hours the "midnight shift".
Note here that even though the bedtime can be set after midnight, there won't be any hours paid at the bedtime rate because they will be superseded by the midnight shift rate. The same is true regarding the start time.
We can see that any hour worked before midnight and after bedtime (the "bedtime shift") has a pay rate that supersedes any hour worked before midnight that is also before bedtime (the "regular shift").
The hours worked by the set consisting of the regular, bedtime, and midnight shifts are mutually exclusive (i.e. the hours do not overlap) and collectively exhaustive (i.e. there are no hours worked not accounted for) with respect to the total shift.
Alternately worded --and more to the point for the purpose of this exercise-- given a pay rate for each sub-shift, calculating the hours in each sub-shift is necessary and sufficient for calculating the total pay.
Some constraints and observations on our inputs:
The start time must be before 4am.
The end time must be after the start time.
The bedtime must not be before the start time.
The bedtime must not be after the end time.
The bedtime can be the same as either the start or end time.
The inputs are in the form of times of the day and are constrained by the kata to be from 5pm to 4am, excluding partial hours. Therefore there are a total of 12 possible values that can be used as inputs (ignoring the constraints mentioned in the previouse bullet points).
To make the calculations easier, we can make a method to covert the times to integers (e.g. 5pm becomes 17, 4am becomes 28) Extending past 24 to keep calculation easier.
Basic walkthrough of what our solution will do
It will take three inputs:
The start time.
The end time.
The bedtime.
It will convert them to the 17-28 integer scale.
It will validate the converted inputs to check whether they violate constraints (i.e. it will check that the inputs "make sense" with respect to one another).
It will used the converted inputs to calculate the amount of hours worked during each sub-shift in order of precedence of the pay rates:
Late Hour shift.
Bedtime shift.
Normal Hour shift.
It will multiply each sub-shift by its pay rate then add those sub-totals and return the total pay amount.
