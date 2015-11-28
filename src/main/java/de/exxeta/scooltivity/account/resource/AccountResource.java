package de.exxeta.scooltivity.account.resource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nimbusds.jose.JOSEException;

import de.exxeta.scooltivity.account.exception.AccountAlreadyExistException;
import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.account.exception.UnauthorizedException;
import de.exxeta.scooltivity.account.service.AccountService;
import de.exxeta.scooltivity.account.util.Constants;
import de.exxeta.scooltivity.filter.AuthRequired;
import de.exxeta.scooltivity.persistence.model.Account;
import de.exxeta.scooltivity.persistence.model.Credentials;
import de.exxeta.scooltivity.persistence.model.Login;

@Path("/accounts")
public class AccountResource {

  private AccountService accountService;

  @Inject
  public AccountResource(AccountService accountService) {
    this.accountService = accountService;
  }

  @GET
  @Path("/status")
  @Produces(MediaType.TEXT_PLAIN)
  public String getStatus() {
    return "AccountResource is alive";
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response register(Account account) {
    Login login = null;
    try {
      login = accountService.registerAccount(account);
      return Response.ok(login).build();
    } catch (GeneralSecurityException | IOException | JOSEException | ParseException e) {
      return Response.serverError().header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    } catch (AccountAlreadyExistException e) {
      return Response.status(Status.CONFLICT).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    } catch (UnauthorizedException e) {
      return Response.status(Status.UNAUTHORIZED).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    } catch (AccountNotFoundException | SchoolNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }

  }

  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(Credentials credentials) {
    Login login = null;
    try {
      login = accountService.login(credentials.getEmail(), credentials.getSchoolName(), credentials.getPassword());
      return Response.ok(login).build();
    } catch (UnauthorizedException e) {
      return Response.status(Status.UNAUTHORIZED).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    } catch (GeneralSecurityException | IOException | JOSEException e) {
      return Response.serverError().header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    } catch (SchoolNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }

  }

  @AuthRequired
  @GET
  @Path("/email/{email}/school/{schoolName}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAccount(@PathParam("email") String email, @PathParam("schoolName") String schoolName) {

    Account account = null;
    try {
      account = accountService.getAccount(email, schoolName);
      account.setPassword(null);
      account.setPasswordSalt(null);
      account.setToken(null);
      return Response.ok(account).build();
    } catch (SchoolNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }

  }

}
