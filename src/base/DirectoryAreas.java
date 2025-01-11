package base;

import base.Visitor.VisitorFindAreaByID;
import base.Visitor.VisitorListPartitionDoors;
import base.Visitor.VisitorPartitionList;
import base.areas.Area;
import base.areas.Partition;
import base.areas.Space;

import java.util.ArrayList;

public final class DirectoryAreas {
  //Singleton instance. Initilitzes all Spaces and Partitions.
  private static Partition root;
  private static DirectoryAreas instance;

  public static DirectoryAreas getInstance() {
    if (instance == null) {
      instance = new DirectoryAreas();
    }
    return instance;
  }

  private DirectoryAreas() {
    //Spaces
    Partition building = new Partition("building", null);
    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor_1 = new Partition("floor1", building);

    //Partitions
    new Space("parking", basement);
    new Space("hall", ground_floor);
    new Space("room1", ground_floor);
    new Space("room2", ground_floor);

    new Space("room3", floor_1);
    new Space("corridor", floor_1);
    new Space("IT", floor_1);

    new Space("stairs", building);
    new Space("exterior", building);

    root = building;

  }

  //Method to find an Area by its ID. It uses the Visitor pattern to search for the area
  public Area findAreaById(String id) {
    if (id.equals("ROOT")) {return root;}
    // Special id that means that the wanted area is the root.
    // This is because the Flutter app client doesn't know the
    // id of the root, differently from the simulator
    VisitorFindAreaByID visitor = new VisitorFindAreaByID(id);
    root.accept(visitor);
    return visitor.getFoundArea();
  }

  //Method to get all doors across all partitions. Uses a visitor to collect the doors.
  public ArrayList<Door> getAllDoors() {
    VisitorListPartitionDoors visitor = new VisitorListPartitionDoors();
    root.accept(visitor);
    return visitor.getDoors();
  }

  //Method to get all spaces in the area. Uses a visitor to collect spaces.
  public ArrayList<Space> getAllSpaces() {
    VisitorPartitionList visitor = new VisitorPartitionList();
    root.accept(visitor);
    return visitor.getPartitions();
  }
}

