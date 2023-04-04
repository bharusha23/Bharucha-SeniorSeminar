import java.io.File;  // Import the File class -> From Data Student Activity
import java.io.IOException;  // Import the IOException class to handle errors -> From Data Student Activity
import java.io.FileNotFoundException;  // Import this class to handle errors-> From Data Student Activity
import java.util.*; // Import the Scanner class to read text files & import ArrayList & import Arrays.asList

public class SeminarMethods {
  ArrayList<Student> studentList = new ArrayList <Student>();
  ArrayList<Session> sessionList = new ArrayList<Session>();
  //Session a = new Session("", 0);
  //sessionList.add(a);
  
  public String skipCell(String data) {
    int comma = data.indexOf(",");
    return data.substring(comma+1);
  }//close skipCell
  
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
      System.out.println(sessionList.get(10).getSessName());
    }//close try 
		catch (FileNotFoundException e) {//just in case the file isn't found
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//close catch
  }//close uploadSessionDetails

  
  public void findPopSess() {
    for (int i = 0; i<sessionList.size(); i++) {
      int count = 0;
      for (int j = 0; j<studentList.size(); j++) {
        int[] a = studentList.get(j).getPref();
        for (int l = 0; l<5; l++) {
          if (a[l]==sessionList.get(i).getSessID()) {
            count++;
          }//close if
        }//close for
      }//close for
      sessionList.get(i).setPopularity(count);
      System.out.println(sessionList.get(i).getSessID());
      System.out.println(sessionList.get(i).getPopularity());
    }//close for
  }//close findPopSess

  public void sortSess() {
    
  }


  
}//close SeminarMethods