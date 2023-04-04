import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files
import java.util.ArrayList;//Import ArrayList

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    SeminarMethods sm = new SeminarMethods();
    sm.uploadStudentPrefs();
    sm.uploadSessionDetails();
    sm.findPopSess();
  }
}