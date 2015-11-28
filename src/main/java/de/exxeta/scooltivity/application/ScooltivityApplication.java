package de.exxeta.scooltivity.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import de.exxeta.scooltivity.account.resource.AccountResource;
import de.exxeta.scooltivity.account.resource.ActivityResource;
import de.exxeta.scooltivity.filter.AuthRequiredFeature;

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

    /*
     * Cassandra
     */
    Cluster cassandra = Cluster.builder().addContactPoint(configuration.getCassandraContactPoint())
        .withPort(configuration.getCassandraPort()).build();
    Session session = cassandra.connect(configuration.getCassandraKeyspace());
    MappingManager mappingManager = new MappingManager(session);

    /*
     * Dependency Injection
     */
    Injector injector = Guice.createInjector(new Module[] { new AbstractModule() {

      @Override
      protected void configure() {
        bind(ScooltivityConfiguration.class).toInstance(configuration);
        bind(MappingManager.class).toInstance(mappingManager);
      }
    }, new ScooltivityModule() });

    /*
     * Resources
     */
    environment.jersey().register(injector.getInstance(AccountResource.class));
    environment.jersey().register(injector.getInstance(ActivityResource.class));

    /*
     * Filters
     */

    environment.jersey().register(new AuthRequiredFeature(configuration));
  }

}
