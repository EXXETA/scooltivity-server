package de.exxeta.scooltivity.persistence.model;

public class Credentials {

  public Credentials() {
  }

  private String email;

  private String schoolName;

  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Credentials [email=" + email + ", schoolName=" + schoolName + ", password=" + password + "]";
  }

}
