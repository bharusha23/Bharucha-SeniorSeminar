import java.util.*; // Import the Scanner class to read text files & import ArrayList & import Arrays.asList

public class Student {
  private String name;
  private String email;
  int[] pref = new int[5];
  Session[] studentSchedule = new Session[5];
  int[]placed = new int[5];

  public Student(String n, String e, int first, int second, int third, int fourth, int fifth) {//Student constructor
    name = n;
    email = e;
    pref[0] = first;
    pref[1] = second;
    pref[2] = third;
    pref[3] = fourth;
    pref[4] = fifth;
  }//close Student

  public String getName() {
    return name;
  }//close getName

  public String getEmail() {
    return email;
  }//close getEmail

  public int[] getPref() {
    return pref;
  }//close getPref()
  
  public int getPref(int x) {
    return pref[x];
  }//close getPref(int x)

  public String printPref() {
    return Arrays.toString(pref);
  }//close printPref

  public void setStudentSchedule(int t, Session s) {//put a session in the student's schedule
    studentSchedule[t] = s;
  }//close setStudentSchedule

  public void removePref(int x) {//delete a preference because they got it
    pref[x] = 0;
  }//close removePref

  public int getPlaced(int x) {//return whether the student has been placed in a session or not
    return placed[x];
  }//close getPlaced

  public void setPlaced (int x) {//indicate that a student has been placed in a session
    placed[x] = 1;
  }//close setPlaced

  public void printSchedule() {//print out the student's schedule
    System.out.println(name);
    for (int i = 0; i<5; i++) {
      if (studentSchedule[i] != null) {//this shouldn't be necessary, but because of an error in my code that I can't find, some people aren't getting put into sessions, so it only prints out times when it has sessions
        System.out.println(i+9 + ":00: " + studentSchedule[i].getSessName());
      }//close if      
    }//close for
  }//close printSchedule
}//close Student