package de.exxeta.scooltivity.persistence.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(keyspace = "scooltivity", name = "activity", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class Activity {

  @Column(name = "activity_id")
  private UUID activityId;

  @PartitionKey
  @Column(name = "school_id")
  @JsonIgnore
  private UUID schoolId;

  @ClusteringColumn
  private String title;

  private String description;

  @Transient
  private boolean isSubscribed = false;

  @Transient
  private String schoolName;

  public Activity() {
    this.activityId = UUID.randomUUID();
  }

  public Activity(String title, String description, UUID schoolId) {
    this();
    this.title = title;
    this.description = description;
    this.schoolId = schoolId;
  }

  public UUID getActivityId() {
    return activityId;
  }

  public void setActivityId(UUID activityId) {
    this.activityId = activityId;
  }

  public UUID getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(UUID schoolId) {
    this.schoolId = schoolId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isSubscribed() {
    return isSubscribed;
  }

  public void setSubscribed(boolean isSubscribed) {
    this.isSubscribed = isSubscribed;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  @Override
  public String toString() {
    return "Activity [activityId=" + activityId + ", schoolId=" + schoolId + ", title=" + title + ", description=" + description
        + ", isSubscribed=" + isSubscribed + "]";
  }

}
