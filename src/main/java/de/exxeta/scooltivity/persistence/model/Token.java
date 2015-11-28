package de.exxeta.scooltivity.persistence.model;

public class Token {

  public Token() {
  }

  public Token(Account account, School school) {
    super();
    this.email = account.getEmail();
    this.schoolName = school.getSchoolName();
    this.type = account.getType();
  }

  private String email;

  private String schoolName;

  private AccountType type;

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

  public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Token [email=" + email + ", schoolName=" + schoolName + ", type=" + type + "]";
  }

}
