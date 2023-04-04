public class Session {
  String sessName;
  int sessID;
  int numSess;
  int popularity;
  Student[] rost1 = new Student[16];
  Student[] rost2 = new Student[16];

  public Session(String sn, int sID) {
    sessName = sn;
    sessID = sID;
    numSess = 0;
  }

  public String getSessName() {
    return sessName;
  }

  public int getSessID() {
    return sessID;
  }

  public void setPopularity(int p) {
    popularity = p;
  }

  public int getPopularity() {
    return popularity;
  }
}