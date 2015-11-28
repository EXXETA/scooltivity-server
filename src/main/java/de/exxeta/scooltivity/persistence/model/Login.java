package de.exxeta.scooltivity.persistence.model;

public class Login {

  public Login() {
  }

  public Login(String token, String role) {
    this.token = token;
    this.role = role;
  }

  private String token;

  private String role;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "LoginResponse [token=" + token + ", role=" + role + "]";
  }

}
