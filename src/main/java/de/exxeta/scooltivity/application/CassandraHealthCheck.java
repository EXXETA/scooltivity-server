package de.exxeta.scooltivity.application;

import com.codahale.metrics.health.HealthCheck;
import com.datastax.driver.core.Session;

public class CassandraHealthCheck extends HealthCheck {

  private Session session;

  public CassandraHealthCheck(Session session) {
    this.session = session;

  }

  @Override
  protected Result check() throws Exception {
    if (session.getState().getConnectedHosts().size() > 0) {
      return Result.healthy();
    }
    return Result.unhealthy("No connections to host!");
  }

}
