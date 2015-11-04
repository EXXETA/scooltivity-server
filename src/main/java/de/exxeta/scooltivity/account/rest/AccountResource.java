package de.exxeta.scooltivity.account.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
public class AccountResource {

  @GET
  @Path("/status")
  @Produces(MediaType.TEXT_PLAIN)
  public String getStatus() {
    return "AccountResource is alive";
  }

}
