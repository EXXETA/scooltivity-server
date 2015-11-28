package de.exxeta.scooltivity.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import de.exxeta.scooltivity.application.ScooltivityConfiguration;

@Provider
public class AuthRequiredFeature implements DynamicFeature {

  private ScooltivityConfiguration configuration;

  public AuthRequiredFeature(ScooltivityConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext context) {
    if (resourceInfo.getResourceClass().getAnnotation(AuthRequired.class) != null
        || resourceInfo.getResourceMethod().getAnnotation(AuthRequired.class) != null) {
      context.register(new AuthenticationRequestFilter(configuration));
    }
  }

}
