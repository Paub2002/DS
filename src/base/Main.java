package base;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html


public final class Main {
  public static void main(String[] args) {
    DirectoryUsers directoryUsers = DirectoryUsers.getInstance();
    DirectoryDoors directoryDoors = DirectoryDoors.getInstance();
    DirectoryAreas directoryAreas = DirectoryAreas.getInstance();
    new WebServer();
  }
}
