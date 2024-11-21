package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DirectoryUsers {
  private final ArrayList<User> users = new ArrayList<>();
  private static DirectoryUsers instance = null;

  public static DirectoryUsers getInstance() {
    if (instance == null) {
      instance = new DirectoryUsers();
    }
    return instance;
  }

  private DirectoryUsers() {
    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later

    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking

    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces

    // role creation
    Role adminRole = new Role("Admin", Arrays.asList("lock", "unlock", "open", "close"), List.of("building"), 2024, 2100, 1, 1, null, null, Arrays.asList(1, 2, 3, 4, 5, 6,7));
    Role managerRole = new Role("Manager", Arrays.asList("lock", "unlock", "open", "close"), List.of("building"), 2024, 2025, 9, 3, 8, 20, Arrays.asList(1, 2, 3, 4, 5, 6));
    Role employeeRole = new Role("Employee", Arrays.asList("open", "close", "unlock_shortly"), Arrays.asList("ground_floor", "floor1", "exterior", "stairs"), 2024, 2025, 9, 3, 9, 17, Arrays.asList(1, 2, 3, 4, 5));
    Role temporaryUserRole = new Role("Temporary User", List.of(), List.of(), null, null, null, null, null, null, List.of());

    // users creation with a specific role
    users.add(new User("Ana", "11343", adminRole));
    users.add(new User("Manel", "95783", managerRole));
    users.add(new User("Marta", "05827", managerRole));
    users.add(new User("Ernest", "74984", employeeRole));
    users.add(new User("Eulalia", "43295", employeeRole));
    users.add(new User("Bernat", "12345", temporaryUserRole));
    users.add(new User("Blai", "77532", temporaryUserRole));

  }

  public User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
