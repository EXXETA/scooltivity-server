package de.exxeta.scooltivity.persistence.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "scooltivity", name = "school", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class School {

  public School() {
    this.schoolId = UUID.randomUUID();
  }

  public School(String schoonName) {
    this();
    this.schoolName = schoonName;
  }

  @Column(name = "school_id")
  @NotNull
  private UUID schoolId;

  @PartitionKey
  @Column(name = "school_name")
  private String schoolName;

  public UUID getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(UUID school_id) {
    this.schoolId = school_id;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String school_name) {
    this.schoolName = school_name;
  }

  @Override
  public String toString() {
    return "School [school_id=" + schoolId + ", school_name=" + schoolName + "]";
  }

}
