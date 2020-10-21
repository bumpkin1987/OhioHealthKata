# OhioHealthKata
A small console app that allows a baby sitter to input the times they worked for a night and calculate how much to charge the client.
******************************************************************************************************************************************

******************************************************************************************************************************************
What you’ll need :
  text editor or IDE or command prompt open
  JDK 6 or later
  Gradle

******************************************************************************************************************************************

******************************************************************************************************************************************
  Install Gradle:
  Installing manually

Step 1. Download the latest Gradle distribution
The current Gradle release is version 6.7, released on 14 Oct 2020. The distribution zip file comes in two flavors:

Binary-only
Complete, with docs and sources
If in doubt, choose the binary-only version and browse docs and sources online.

Need to work with an older version? See the releases page.

Step 2. Unpack the distribution
Linux & MacOS users
Unzip the distribution zip file in the directory of your choosing, e.g.:

$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-6.7-bin.zip
$ ls /opt/gradle/gradle-6.7
LICENSE  NOTICE  bin  getting-started.html  init.d  lib  media

                                          **************************************************************************************
  Microsoft Windows users
Create a new directory C:\Gradle with File Explorer.

Open a second File Explorer window and go to the directory where the Gradle distribution was downloaded. Double-click the ZIP archive to expose the content. Drag the content folder gradle-6.7 to your newly created C:\Gradle folder.

Alternatively you can unpack the Gradle distribution ZIP into C:\Gradle using an archiver tool of your choice.

Step 3. Configure your system environment
Linux & MacOS users
Configure your PATH environment variable to include the bin directory of the unzipped distribution, e.g.:

 $ export PATH=$PATH:/opt/gradle/gradle-6.7/bin
Microsoft Windows users
In File Explorer right-click on the This PC (or Computer) icon, then click Properties -> Advanced System Settings -> Environmental Variables.

Under System Variables select Path, then click Edit. Add an entry for C:\Gradle\gradle-6.7\bin. Click OK to save.

Step 4. Verify your installation
Open a console (or a Windows command prompt) and run gradle -v to run gradle and display the version, e.g.:

$ gradle -v

------------------------------------------------------------
Gradle 6.7
------------------------------------------------------------

******************************************************************************************************************************************

******************************************************************************************************************************************
HOW TO RUN THE APP ON THE COMMAND LINE:
1. After Java and Gradle have been installed (make sure PATH's in environment variables updated) i.e. JAVA_HOME = location of java on your local machine
2. Go into the directory where the app was pulled too. (i.e.-> C:..\Desktop\OhioHealthKata)
3. cd into that directory
4. Type in terminal: gradle build and hit enter and look for Build SUCCESSFULL
5. Then type gradle run

That should kick off the app!

******************************************************************************************************************************************

******************************************************************************************************************************************
KATA DESCRIPTION:
This kata simulates a babysitter working and getting paid for one night. The rules are pretty straight forward:

The babysitter

starts no earlier than 5:00PM
leaves no later than 4:00AM
gets paid $12/hour from start-time to bedtime
gets paid $8/hour from bedtime to midnight
gets paid $16/hour from midnight to end of job
gets paid for full hours (no fractional hours)
Feature: As a babysitter In order to get paid for 1 night of work I want to calculate my nightly charge

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
Convert to military time and calculate the hours worked given certain conditions of when those hours were worked.
