package de.exxeta.scooltivity.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import de.exxeta.scooltivity.account.rest.AccountResource;

public class ScooltivityApplication extends Application<ScooltivityConfiguration> {

  public static void main(String[] args) throws Exception {
    new ScooltivityApplication().run(args);
  }

  @Override
  public String getName() {
    return "Scooltivity";
  }

  @Override
  public void initialize(Bootstrap<ScooltivityConfiguration> bootstrap) {
    // nothing to do yet
  }

  @Override
  public void run(ScooltivityConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new AccountResource());
  }

}
