package de.exxeta.scooltivity.application;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScooltivityConfiguration extends Configuration {

  @NotEmpty
  private String cassandraContactPoint;

  @NotNull
  private Integer cassandraPort;

  @NotEmpty
  private String cassandraKeyspace;

  @NotEmpty
  private String sharedSecretString;

  @JsonProperty
  public String getCassandraContactPoint() {
    return cassandraContactPoint;
  }

  @JsonProperty
  public void setCassandraContactPoint(String cassandraContactPoint) {
    this.cassandraContactPoint = cassandraContactPoint;
  }

  @JsonProperty
  public Integer getCassandraPort() {
    return cassandraPort;
  }

  @JsonProperty
  public void setCassandraPort(Integer cassandraPort) {
    this.cassandraPort = cassandraPort;
  }

  @JsonProperty
  public String getCassandraKeyspace() {
    return cassandraKeyspace;
  }

  @JsonProperty
  public void setCassandraKeyspace(String cassandraKeyspace) {
    this.cassandraKeyspace = cassandraKeyspace;
  }

  public String getSharedSecretString() {
    return sharedSecretString;
  }

  public void setSharedSecretString(String sharedSecretString) {
    this.sharedSecretString = sharedSecretString;
  }

}
