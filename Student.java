import java.util.*;

public class Student {
  private String name;
  private String email;
  int[] pref = new int[5];

  public Student(String n, String e, int first, int second, int third, int fourth, int fifth) {
    name = n;
    email = e;
    pref[0] = first;
    pref[1] = second;
    pref[2] = third;
    pref[3] = fourth;
    pref[4] = fifth;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int[] getPref() {
    return pref;
  }

  public String printPref() {
    return Arrays.toString(pref);
  }
}