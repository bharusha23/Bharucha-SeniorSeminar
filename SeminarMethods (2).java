import java.io.File;  // Import the File class -> From Data Student Activity
import java.io.IOException;  // Import the IOException class to handle errors -> From Data Student Activity
import java.io.FileNotFoundException;  // Import this class to handle errors-> From Data Student Activity
import java.util.*; // Import the Scanner class to read text files & import ArrayList & import Arrays.asList

public class SeminarMethods {
  ArrayList<Student> studentList = new ArrayList <Student>();
  ArrayList<Session> sessionList = new ArrayList<Session>();
  ArrayList<Session> sortedSessList;
  Session[][]schedule = new Session[5][5];

  
  public String skipCell(String data) {//simple method to condense code
    int comma = data.indexOf(",");
    return data.substring(comma+1);
  }//close skipCell

  //use file reading to make student objects and put them into an ArrayList
  public void uploadStudentPrefs() {
    try {
			File myObj = new File("SeniorSeminarPreferences.csv"); //get a new file
			Scanner myReader = new Scanner(myObj);//scan in the new file
			while (myReader.hasNextLine()) {//if there is still information in the file
				String data = myReader.nextLine();//data = the next set of information
				data = skipCell(data); //clear timestamp
				int comma = data.indexOf(",");
				String email1 = data.substring(0, comma); //get the email
				data = data.substring(comma+1);
        data = skipCell(data); //clear name2
				comma = data.indexOf(",");
				String name1 = data.substring(0, comma); //get the name
				data = data.substring(comma+1);
        for (int i = 0; i<6; i++) { //clear written preferences & name22
          data = skipCell(data);
        } //close for loop
        comma = data.indexOf(",");
        int first = Integer.parseInt(data.substring(0, comma)); //get 1st pref
        data = data.substring(comma+1);
        comma = data.indexOf(",");
        int se = Integer.parseInt(data.substring(0, comma)); //get 2nd pref
        data = data.substring(comma+1);
        comma = data.indexOf(",");
        int th = Integer.parseInt(data.substring(0, comma)); //get 3rd pref
        data = data.substring(comma+1);        
        comma = data.indexOf(",");
        int fo = Integer.parseInt(data.substring(0, comma)); //get 4th pref
        data = data.substring(comma+1);
        int fifth = Integer.parseInt(data); //get 5th pref     
        
				studentList.add(new Student(name1, email1, first, se, th, fo, fifth));//add a new instance of the Person class to guestList
			}//close while
			myReader.close();
		}//close try 
		catch (FileNotFoundException e) {//just in case the file isn't found
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//close catch
  }//close uploadStudentPrefs

  //collect all the different Session information into objects and store them in an ArrayList
  public void uploadSessionDetails() {
    try {
  			File myObj = new File("SeniorSeminarSessions.csv"); //get a new file
  			Scanner myReader = new Scanner(myObj);//scan in the new file
  			while (myReader.hasNextLine()) {//if there is still information in the file
  				String data = myReader.nextLine();//data = the next set of information
          int comma = data.indexOf(",");
				  String sename = data.substring(0, comma); //get the session name
				  data = data.substring(comma+1);
          comma = data.indexOf(",");
          int senum = Integer.parseInt(data.substring(0, comma)); //get sessionID
          sessionList.add(new Session(sename, senum));//add a new instance of the Session class to sessionList
        }//close while
    }//close try 
		catch (FileNotFoundException e) {//just in case the file isn't found
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//close catch
  }//close uploadSessionDetails

  //give every session a rating based on how many students selected them as a choice
  public void findPopSess() {
    for (int i = 0; i<sessionList.size(); i++) {
      int count = 0;
      for (int j = 0; j<studentList.size(); j++) {
        int[] a = studentList.get(j).getPref();
        for (int l = 0; l<5; l++) {
          if (a[l]==sessionList.get(i).getSessID()) {//if a student listed this session as one of their selected system
            count++;//increase their popularity
          }//close if
        }//close for
      }//close for
      sessionList.get(i).setPopularity(count);//set the sessions popularity
    }//close for
  }//close findPopSess
  
  //This uses selection sort to sort the list of sessions from most to least popular
  public void sortSess(ArrayList<Session> sessList, int change) {
    //creating a new ArrayList to sort into
    sortedSessList = new ArrayList<Session>();
    for (Session a : sessList) {
      sortedSessList.add(a);
    }//close for each
    if (change == 1) {//this is a simple bit of code to stop one session from appearing in the same place twice, it just decreases the poularity of one session and increases the popularity of the others
      sortedSessList.get(0).downPlace();
      sortedSessList.get(2).upPlace();
    }//close if
    int size = sortedSessList.size();
    for (int s = 0; s<size-1; s++) {
      int max = s;
      for (int i = s+1; i<size; i++) {
        if (sortedSessList.get(i).getPopularity()>sortedSessList.get(max).getPopularity()) {
          max = i; //find the max element in the list
        }//close if
      }//close inner for
      Session x = sortedSessList.get(s);
      sortedSessList.set(s, sortedSessList.get(max));
      sortedSessList.set(max, x); //replace the max position and the first position to sort it
    }//close outer for
  }//close sortSess

  //This places the 5 most popular sessions into different time slots in the same classroom
  public void placeSess(ArrayList<Session> sortedSessList, int r) {
    int room = r;
    int sessionCounter = 0;
    for (int time = 0; time<5; time++) {
      schedule[time][room] = sortedSessList.get(sessionCounter);
      if (sortedSessList.get(sessionCounter).getNumSess() == 0) {//if the sesssion has not been run yet
        sortedSessList.get(sessionCounter).setFirstSessTime(time);//store when the first session has been run
      }//close if
      sortedSessList.get(sessionCounter).incrementNumSess();//note that session has been run
      sortedSessList.get(sessionCounter).subtractPopularity();//assume that 16 people will be slotted in here
      if (sortedSessList.get(sessionCounter).getNumSess() > 1) {//if there have been 2 sessions scheduled
        sortedSessList.remove(sessionCounter);//remove the session from the list
        sessionCounter--;//go back one to recheck the list
      }//close if
      sessionCounter++;
    }//close for
  }//close placeSess

  //This goes through and orders and places each session into all of the rooms
  public void makeSchedule() {
    sortSess(sessionList, 0);
    placeSess(sortedSessList, 0);
    sortSess(sortedSessList, 1);
    placeSess(sortedSessList, 1);
    sortSess(sortedSessList, 0);
    placeSess(sortedSessList, 2);
    sortSess(sortedSessList, 0);
    placeSess(sortedSessList, 3);
    sortSess(sortedSessList, 0);
    placeSess(sortedSessList, 4);
  }//close makeSchedule()

  //Print out a schedule for when the different sessions will be held
  public void printSchedule() {
    System.out.print("\n");
    for (int i = 0; i<5; i++) {
      System.out.println(i+9 + ":00 sessions");
      for (int j = 0; j<5; j++) {
        System.out.println("\t" + schedule[i][j].getSessName());
      }//close for
      System.out.print("\n");
    }//close for
  }//close printSchedule

  //This function 
  public void placeStudents() {
    for (int time = 0; time<5; time++) {
      for (int room = 0; room<5; room++) {
        for (int pref = 0; pref<5; pref++) {
          for (int stu = 0; stu<studentList.size(); stu++) {
            if (studentList.get(stu).getPref(pref) == schedule[time][room].getSessID() && studentList.get(stu).getPlaced(time) == 0) {//if a student's preference is available & they haven't been placed in this time slot yet
              if (schedule[time][room].getNumSess()==2 && schedule[time][room].getFirstSessTime() != time  && schedule[time][room].rostTwoSize()<16 ) {//if there are two iterations of this session, and the first session is filled up, and there's space in the second session
                schedule[time][room].addStudentTwo(studentList.get(stu));//add the student to the roster for the second session
                studentList.get(stu).removePref(pref);//remove this preference of theirs
                studentList.get(stu).setPlaced(time);//note that they have been placed in this timeslot
                studentList.get(stu).setStudentSchedule(time, schedule[time][room]);//add this session to their schedule
              }//close if
              else if (schedule[time][room].rostOneSize()<16) {//otherwise, if there is space in the first session
                schedule[time][room].addStudentOne(studentList.get(stu));//add student to the roster of the first session
                
                studentList.get(stu).removePref(pref);//remove this preference of theirs
                studentList.get(stu).setPlaced(time);//note that they have been placed in this timeslot
                studentList.get(stu).setStudentSchedule(time, schedule[time][room]);//add this session to their schedule
              }//close else if
            }//close if  
          }//close stu for
        }//close pref for
      }//close room for 
    //This section places all the students that didn't have a preference into a session  
    for (int stu = 0; stu<studentList.size(); stu++) {
      for (int room = 0; room<5; room++) {
        if (studentList.get(stu).getPlaced(time) == 0) {//if the students hasn't been placed into a session for this timeslot
         if (schedule[time][room].getNumSess()==2 && schedule[time][room].rostOneSize()>=16 ) {//if there are two iterations of this session, and the first session is filled up, and there's space in the second session
           if (schedule[time][room].getFirstSessTime() != time && schedule[time][room].rostTwoSize()<16) {
            schedule[time][room].addStudentTwo(studentList.get(stu));//add the student to the roster for the second session
            studentList.get(stu).setPlaced(time);//note that they have been placed in this timeslot
            studentList.get(stu).setStudentSchedule(time, schedule[time][room]);//add this session to their schedule
           }//close if  
         }//close if
        else if (schedule[time][room].getFirstSessTime() == time && schedule[time][room].rostOneSize()<16) {//otherwise, if there is space in the first session
            schedule[time][room].addStudentOne(studentList.get(stu));//add student to the roster of the first session
            studentList.get(stu).setPlaced(time);//note that they have been placed in this timeslot
            studentList.get(stu).setStudentSchedule(time, schedule[time][room]);//add this session to their schedule
          }//close else if
        }//close if
      }//close room for
    }//close stu for  
    }//close time for
  }//close placeStudents

  //&& schedule[time][room].rostOneSize()>=16 
  
  public void printRosters() {
    for (int t = 0; t<5; t++) {
      for (int r = 0; r<5; r++) {
        System.out.println(schedule[t][r].getSessName());
        if (schedule[t][r].getFirstSessTime() == t) {
          for (int i = 0; i<schedule[t][r].rostOneSize(); i++) {
            System.out.println("\t" + schedule[t][r].rostOnePlacement(i));
          }//close i for
        }//close if
        else if (schedule[t][r].getNumSess() == 2) {
          for (int j = 0; j<schedule[t][r].rostTwoSize(); j++) {
            System.out.println("\t" + schedule[t][r].rostTwoPlacement(j));
          } //close j for 
        }//close if
      }//close r for  
      System.out.print("\n\n\n");
    }//close t for
  }//close printRosters

  //Print out the schedule of all the students
  public void printStudentSchedule() {
    for (int i = 0; i<studentList.size(); i++) {
      studentList.get(i).printSchedule();
      System.out.println("\n");
    }  
  }

}//close SeminarMethods
