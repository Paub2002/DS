package baseNoStates;

import java.util.ArrayList;

public final class DirectoryAreas {
 private static Space root;

 public static void makeAreas()
 {
     Space building     = new Space("building", null);
     Space basement     = new Space("basement", building);
     Space ground_floor = new Space("ground_floor", building);
     Space floor_1      = new Space("floor_1", building);

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
public static Partition findPartitionById(String id) {return root.findPartitionById(id);}
public static Area findAreaById(String id){ return root.findAreaById(id); }
public static ArrayList<Door> getAllDoors(){ return root.getDoorsGivingAccess(); }
}

