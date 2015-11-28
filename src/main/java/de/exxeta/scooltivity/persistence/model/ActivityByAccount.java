package de.exxeta.scooltivity.persistence.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "scooltivity", name = "activity_by_account", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class ActivityByAccount {

  public ActivityByAccount(UUID activityId, UUID accountId) {
    this.activityId = activityId;
    this.accountId = accountId;

  }

  @PartitionKey
  @Column(name = "activity_id")
  private UUID activityId;

  @ClusteringColumn
  @Column(name = "account_id")
  private UUID accountId;

  public UUID getActivityId() {
    return activityId;
  }

  public void setActivityId(UUID activityId) {
    this.activityId = activityId;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  @Override
  public String toString() {
    return "ActivityByAccount [activityId=" + activityId + ", accountId=" + accountId + "]";
  }

}
