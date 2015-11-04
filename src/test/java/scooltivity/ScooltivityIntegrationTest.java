package scooltivity;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.ClassRule;
import org.junit.Test;

import de.exxeta.scooltivity.account.rest.AccountResource;

public class ScooltivityIntegrationTest {

  @ClassRule
  public final static ScooltivityTestRule scooltivityTestRule = new ScooltivityTestRule(new AccountResource());

  private Client client = ClientBuilder.newClient();

  @Test
  public void testStatusAccountResource() throws Exception {
    WebTarget target = client.target(scooltivityTestRule.baseUri()).path("accounts").path("status");
    String response = target.request().get(String.class);
    assertEquals("AccountResource is alive", response);
  }
}
