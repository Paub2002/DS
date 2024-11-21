package base;

import base.Visitor.VisitorFindAreaByID;
import base.Visitor.VisitorListPartitionDoors;
import base.Visitor.VisitorPartitionList;
import base.areas.*;

import java.util.ArrayList;

public final class DirectoryAreas {
  //Singleton instance. Initilitzes all Spaces and Partitions.
  private static Space root;
  private static DirectoryAreas instance;

  public static DirectoryAreas getInstance() {
    if (instance == null) {
      instance = new DirectoryAreas();
    }
    return instance;
  }

  private DirectoryAreas()
  {
    //Spaces
    Space building     = new Space("building", null);
    Space basement     = new Space("basement", building);
    Space ground_floor = new Space("ground_floor", building);
    Space floor_1      = new Space("floor1", building);

    //Partitions
    Partition parking = new Partition("parking",basement);
    Partition hall = new Partition("hall", ground_floor);
    Partition room_1 = new Partition("room1",ground_floor);
    Partition room_2 = new Partition("room2", ground_floor);

    Partition room_3 = new Partition("room3", floor_1);
    Partition corridor = new Partition("corridor", floor_1);
    Partition it = new Partition("IT", floor_1);

    Partition stairs = new Partition("stairs",     building);
    Partition exterior = new Partition("exterior", building);

    root = building;

  }
  //Method to find an Area by its ID. It uses the Visitor pattern to search for the area
  public Area findAreaById(String id) {
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
  public ArrayList<Partition> getAllSpaces() {
    VisitorPartitionList visitor = new VisitorPartitionList();
    root.accept(visitor);
    return visitor.getPartitions();
  }
}

