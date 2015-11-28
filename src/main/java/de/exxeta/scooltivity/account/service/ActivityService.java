package de.exxeta.scooltivity.account.service;

import java.util.List;
import java.util.UUID;

import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.ActivityNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.persistence.model.Activity;

public interface ActivityService {

  public void add(Activity activity) throws SchoolNotFoundException;

  public void subscribeAccount(String email, UUID activityId) throws ActivityNotFoundException, AccountNotFoundException;

  public void unsubscribeAccount(String email, UUID activityId) throws ActivityNotFoundException, AccountNotFoundException;

  public List<Activity> getActivities(String email, String schoolName) throws SchoolNotFoundException, AccountNotFoundException;

}
