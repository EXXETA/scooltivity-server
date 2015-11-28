package de.exxeta.scooltivity;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;

import de.exxeta.scooltivity.account.resource.AccountResource;
import de.exxeta.scooltivity.account.service.AccountServiceImpl;

public class ScooltivityIntegrationTest {

  private static AccountServiceImpl accountService = new AccountServiceImpl();

  @ClassRule
  public final static ScooltivityTestRule scooltivityTestRule = new ScooltivityTestRule(new AccountResource(accountService));

  private Client client = ClientBuilder.newClient();

  @AfterClass
  public static void releaseClass() throws Exception {
    scooltivityTestRule.closeCassandraConnection();
  }

  @Test
  public void testStatusAccountResource() throws Exception {
    WebTarget target = client.target(scooltivityTestRule.baseUri()).path("accounts").path("status");
    String response = target.request().get(String.class);
    assertEquals("AccountResource is alive", response);
  }

}
