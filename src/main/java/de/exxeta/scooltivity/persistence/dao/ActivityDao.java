package de.exxeta.scooltivity.persistence.dao;

import java.util.List;
import java.util.UUID;

import de.exxeta.scooltivity.persistence.model.Activity;

public interface ActivityDao {

  public void save(Activity account);

  public Activity findById(UUID activityId);

  public List<Activity> findBySchoolId(UUID schoolId);

}
