package de.exxeta.scooltivity.account.service;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.ActivityNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.persistence.dao.AccountDao;
import de.exxeta.scooltivity.persistence.dao.ActivityByAccountDao;
import de.exxeta.scooltivity.persistence.dao.ActivityDao;
import de.exxeta.scooltivity.persistence.dao.SchoolDao;
import de.exxeta.scooltivity.persistence.model.Account;
import de.exxeta.scooltivity.persistence.model.Activity;
import de.exxeta.scooltivity.persistence.model.ActivityByAccount;
import de.exxeta.scooltivity.persistence.model.School;

public class ActivityServiceImpl implements ActivityService {

  @Inject
  private ActivityDao activityDao;

  @Inject
  private SchoolDao schoolDao;

  @Inject
  private AccountDao accountDao;

  @Inject
  private ActivityByAccountDao activityByAccountDao;

  public ActivityServiceImpl() {
  }

  @Override
  public void add(Activity activity) throws SchoolNotFoundException {
    // get school
    School school = getSchool(activity.getSchoolName());

    // create activity
    activity.setSchoolId(school.getSchoolId());
    activityDao.save(activity);
  }

  @Override
  public void subscribeAccount(String email, UUID activityId) throws ActivityNotFoundException, AccountNotFoundException {
    Activity activity = getActivity(activityId);
    Account account = getAccount(email, activity.getSchoolId());

    ActivityByAccount activityByAccount = new ActivityByAccount(activity.getActivityId(), account.getAccountId());
    activityByAccountDao.save(activityByAccount);
  }

  @Override
  public void unsubscribeAccount(String email, UUID activityId) throws ActivityNotFoundException, AccountNotFoundException {
    Activity activity = getActivity(activityId);
    Account account = getAccount(email, activity.getSchoolId());

    activityByAccountDao.delete(account.getAccountId(), activity.getActivityId());
  }

  @Override
  public List<Activity> getActivities(String email, String schoolName) throws SchoolNotFoundException, AccountNotFoundException {
    School school = getSchool(schoolName);
    Account account = getAccount(email, school.getSchoolId());

    List<Activity> schoolActivities = activityDao.findBySchoolId(school.getSchoolId());

    if (!schoolActivities.isEmpty()) {
      List<UUID> accountActivityIds = activityByAccountDao.findByAccountId(account.getAccountId());
      if (!accountActivityIds.isEmpty()) {
        markActivitiesAsSubscribed(schoolActivities, accountActivityIds);
      }

    }

    return schoolActivities;
  }

  private void markActivitiesAsSubscribed(List<Activity> schoolActivities, List<UUID> accountActivityIds) {

    for (Activity schoolActivity : schoolActivities) {
      for (UUID accountActivityId : accountActivityIds) {
        if (accountActivityId.equals(schoolActivity.getActivityId())) {
          schoolActivity.setSubscribed(true);
        }
      }
    }
  }

  private School getSchool(String schoolName) throws SchoolNotFoundException {
    School school = schoolDao.getOne(schoolName);
    if (school == null) {
      throw new SchoolNotFoundException(schoolName);
    }
    return school;
  }

  private Account getAccount(String email, UUID schoolId) throws AccountNotFoundException {
    Account account = accountDao.getOne(email, schoolId);
    if (account == null) {
      throw new AccountNotFoundException(email, schoolId);
    }
    return account;
  }

  private Activity getActivity(UUID activityId) throws ActivityNotFoundException {
    Activity activity = activityDao.findById(activityId);
    if (activity == null) {
      throw new ActivityNotFoundException(activityId);
    }
    return activity;
  }

}
