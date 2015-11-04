package scooltivity;

import io.dropwizard.testing.junit.DropwizardClientRule;

import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

public class ScooltivityTestRule extends DropwizardClientRule {

  private MappingManager mappingManager;

  public ScooltivityTestRule(Object... resources) {
    super(resources);

    initCassandra();
  }

  private void initCassandra() {
    try {
      EmbeddedCassandraServerHelper.startEmbeddedCassandra();
      Thread.sleep(3000);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
    Cluster cassandra = Cluster.builder().addContactPoint("localhost").withPort(9142).build();
    Session session = cassandra.connect();
    CQLDataLoader dataLoader = new CQLDataLoader(session);
    dataLoader.load(new ClassPathCQLDataSet("createTable.cql", "scooltivity"));
    session = cassandra.connect("scooltivity");
    mappingManager = new MappingManager(session);
  }

  public void closeCassandraConnection() throws Exception {
    System.out.println("Close cassandra connection");
    Cluster cluster = mappingManager.getSession().getCluster();
    mappingManager.getSession().close();
    cluster.close();
  }

  public MappingManager getMappingManager() {
    return mappingManager;
  }

}
