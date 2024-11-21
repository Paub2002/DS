package base;

import base.areas.Area;
import base.areas.Partition;
import base.areas.Space;

public final class DirectoryAreas {
    //Singleton instance. Initializes all Spaces and Partitions.
 private final Space root;
 private static DirectoryAreas instance; // Singleton instance

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
    new Partition("parking",basement);
    new Partition("hall", ground_floor);
    new Partition("room1",ground_floor);
    new Partition("room2", ground_floor);
    new Partition("room3", floor_1);
    new Partition("corridor", floor_1);
    new Partition("IT", floor_1);

    new Partition("stairs",     building);
    new Partition("exterior", building);

     root = building;

 }
public Partition findPartitionById(String id) {return root.findPartitionById(id);}
public Area findAreaById(String id){ return root.findAreaById(id); }
// --Commented out by Inspection (21/11/2024 08:37):public ArrayList<Door> getAllDoors(){ return root.getDoorsGivingAccess(); }
}

