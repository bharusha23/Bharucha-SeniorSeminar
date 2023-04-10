/** Senior Seminar Code - By Shanaya
* More information in the README File
*The problem this code is solving for is creating a schedule-conflict minimization for Senior Seminars
* There are 5 timeslots and 5 rooms to be filled per timeslot
* There are 18 sessions & 74 students; 16 students max per session
* All the students have given their preferences & now a schedule must be made and students must be assigned
**/

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files
import java.util.ArrayList;//Import ArrayList

class Main {
  public static void main(String[] args) {
    SeminarMethods sm = new SeminarMethods();//create a new instance of the Seminar Methods class
    sm.uploadStudentPrefs();//upload the student preferences data
    sm.uploadSessionDetails();//upload all the details about the Senior Seminars
    sm.findPopSess();//reorder the session list in terms of popularity of the session
    sm.makeSchedule();//map out the schedule based on popularity
    sm.printSchedule();
    sm.placeStudents();//put students into sessions based on their preferences
    sm.printRosters();//print out the rosters of all the Sessions
    sm.printStudentSchedule();
  }//close public static void main
}//close Main