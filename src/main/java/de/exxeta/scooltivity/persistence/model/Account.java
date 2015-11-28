package de.exxeta.scooltivity.persistence.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(keyspace = "scooltivity", name = "account", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class Account {

  public Account() {
    this.accountId = UUIDs.random();
  }

  @Column(name = "account_id")
  @JsonIgnore
  private UUID accountId;

  @PartitionKey
  @NotEmpty
  private String email;

  @NotEmpty
  @Column(name = "first_name")
  private String firstName;

  @NotEmpty
  @Column(name = "last_name")
  private String lastName;

  @NotEmpty
  @ClusteringColumn
  @Column(name = "school_id")
  @JsonIgnore
  private UUID schoolId;

  @NotNull
  @Column(name = "type")
  private AccountType type;

  private Date birthday;

  @NotNull
  private String password;

  @NotNull
  @Column(name = "password_salt")
  @JsonIgnore
  private String passwordSalt;

  @Column(name = "auth_token")
  private String token;

  @Column(name = "registered_by")
  @JsonIgnore
  private UUID registeredBy;

  @Transient
  private String schoolName;

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

  public UUID getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(UUID schoolId) {
    this.schoolId = schoolId;
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

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UUID getRegisteredBy() {
    return registeredBy;
  }

  public void setRegisteredBy(UUID registeredBy) {
    this.registeredBy = registeredBy;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  @Override
  public String toString() {
    return "Account [accountId=" + accountId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", schoolId="
        + schoolId + ", type=" + type + ", birthday=" + birthday + ", password=" + password + ", passwordSalt=" + passwordSalt + ", token="
        + token + ", registeredBy=" + registeredBy + ", schoolName=" + schoolName + "]";
  }

}
