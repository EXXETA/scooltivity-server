package de.exxeta.scooltivity.account.resource;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.ActivityNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.account.service.ActivityService;
import de.exxeta.scooltivity.account.util.Constants;
import de.exxeta.scooltivity.filter.AuthRequired;
import de.exxeta.scooltivity.persistence.model.Activity;

@Path("/activities")
public class ActivityResource {

  private ActivityService activityService;

  @Inject
  public ActivityResource(ActivityService activityService) {
    this.activityService = activityService;

  }

  @GET
  @Path("/status")
  @Produces(MediaType.TEXT_PLAIN)
  public String getStatus() {
    return "ActivityResource is alive";
  }

  @AuthRequired
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addActivity(Activity activity) {
    try {
      activityService.add(activity);
      return Response.ok(activity).build();
    } catch (SchoolNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }
  }

  @AuthRequired
  @POST
  @Path("{activityId}/subscriptions/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response subscribeActivityForAccount(@PathParam("activityId") UUID activityId, @PathParam("email") String email) {
    try {
      activityService.subscribeAccount(email, activityId);
      return Response.ok().build();
    } catch (ActivityNotFoundException | AccountNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }
  }

  @AuthRequired
  @DELETE
  @Path("{activityId}/subscriptions/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response unsubscribeActivityForAccount(@PathParam("activityId") UUID activityId, @PathParam("email") String email) {
    try {
      activityService.unsubscribeAccount(email, activityId);
    } catch (ActivityNotFoundException | AccountNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }
    return Response.ok().build();
  }

  @AuthRequired
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getActivitieForAccount(@QueryParam("email") String email, @QueryParam("schoolName") String schoolName) {
    try {
      List<Activity> accountActivities = activityService.getActivities(email, schoolName);
      return Response.ok(accountActivities).build();
    } catch (SchoolNotFoundException | AccountNotFoundException e) {
      return Response.status(Status.NOT_FOUND).header(Constants.X_SCOOLTIVITY_ERROR, e.getMessage()).build();
    }
  }

}
