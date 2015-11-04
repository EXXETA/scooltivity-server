package de.exxeta.scooltivity.persistence.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "scooltivity", name = "accounts", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class Account {

  public Account() {
    this.accountId = UUIDs.random();
  }

  @Column(name = "account_id")
  private UUID accountId;

  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "school_name")
  private String schoolName;

  @Column(name = "account_type")
  private AccountType type;

  private Date birthday;

  private String password;

  private String token;

  public UUID getAccountId() {
    return accountId;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "Account [accountId=" + accountId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", schoolName="
        + schoolName + ", type=" + type + ", birthday=" + birthday + ", password=" + password + ", token=" + token + "]";
  }

}
