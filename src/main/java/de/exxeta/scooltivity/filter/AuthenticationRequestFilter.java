package de.exxeta.scooltivity.filter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.nimbusds.jose.JOSEException;

import de.exxeta.scooltivity.account.util.Constants;
import de.exxeta.scooltivity.account.util.TokenUtils;
import de.exxeta.scooltivity.application.ScooltivityConfiguration;

@Provider
public class AuthenticationRequestFilter implements ContainerRequestFilter {

  private ScooltivityConfiguration configuration;

  public AuthenticationRequestFilter(ScooltivityConfiguration configuration) {
    this.configuration = configuration;

  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String token = requestContext.getHeaderString(Constants.X_AUTH_TOKEN);

    if (token == null) {
      throw new WebApplicationException("Authentication token was not transferred", Response.Status.UNAUTHORIZED);
    }

    try {
      if (!TokenUtils.verifyToken(token, configuration.getSharedSecretString())) {
        throw new WebApplicationException("Authentication token is invalid", Response.Status.UNAUTHORIZED);
      }

    } catch (GeneralSecurityException | JOSEException | ParseException e) {
      throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
    }
  }
}
