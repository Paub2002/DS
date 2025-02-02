package base;

public class User {
  // User class handles authentication and authorization.

  private final String name;
  private final String credential;
  private final Role role;

  public User(String name, String credential, Role role) {
    this.name = name;
    this.credential = credential;
    this.role = role;
  }

  public String getCredential() {
    return credential;
  }

  public Role getRole() {
    return role;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + ", role=" + role.getName() + "}";
  }
}
