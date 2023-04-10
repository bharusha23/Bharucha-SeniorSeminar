import java.util.*; // Import the Scanner class to read text files & import ArrayList & import Arrays.asList

public class Session {
  String sessName;
  int sessID;
  int numSess;
  int popularity;
  int firstSessTime = -1;
  ArrayList <Student> rost1 = new ArrayList <Student>();
  ArrayList <Student> rost2 = new ArrayList <Student>();

  public Session(String sn, int sID) {//Session constructor
    sessName = sn;
    sessID = sID;
    numSess = 0;
  }//close Session

  public String getSessName() {//acess the session name
    return sessName;
  }//close getSessName

  public int getSessID() {//get the ID of the session
    return sessID;
  }//close getSessID

  public int getNumSess() {//get how many times this session has been scheduled
    return numSess;
  }//close getNumSess

  public void setPopularity(int p) {//store how popular this session is
    popularity = p;
  }//close setPopularity

  public int getPopularity() {//get this session's popularity
    return popularity;
  }//close getPopularity

  public void incrementNumSess() {//not that the session is being run again
    numSess++;
  }//close incrementNumSess

  public void subtractPopularity() {//decrease the popularity after the session has already been offered once
    popularity = popularity-16;
  }//close subtractPopularity

  public void upPlace(){//change the position of the sessions
    popularity = popularity+7;
  }//close upPlace

  public void downPlace(){//change the position of the sessions
    popularity = popularity-7;
  }//close downPlace

  public void addStudentOne(Student s) {//add a student to the first session
    rost1.add(s);
  }//close addStudentOne

  public void addStudentTwo(Student s) {//add a student to the second session
    rost2.add(s);
  }//close addStudentTwo

  public void setFirstSessTime(int t) {//record the time the first session is being run
    firstSessTime = t;
  }//closeFirstSessTime

  public int getFirstSessTime() {//get the time the first session is being run
    return firstSessTime;
  }//close getFirstSessTime

  public int rostOneSize() {//get the size of the first roster
    return rost1.size();
  }//close rostOneSize

  public int rostTwoSize() {//get the size of the second roster
    return rost2.size();
  }//close rostTwoSize

  public String rostOnePlacement(int x) {//get the name of the student in the first roster in this position
    return rost1.get(x).getName();
  }//close rostOnePlacement

  public String rostTwoPlacement(int x) {//get the name of the student in the second roster in this position
    return rost2.get(x).getName();
  }//close rostTwoPlacement
}//close Session