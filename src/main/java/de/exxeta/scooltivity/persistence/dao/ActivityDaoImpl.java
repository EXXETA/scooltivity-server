package de.exxeta.scooltivity.persistence.dao;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import de.exxeta.scooltivity.persistence.model.Activity;

public class ActivityDaoImpl implements ActivityDao {

  private Mapper<Activity> activityMapper;
  private ActivityAccessor activityAccessor;

  @Inject
  public ActivityDaoImpl(MappingManager mappingManager) {
    this.activityMapper = mappingManager.mapper(Activity.class);
    this.activityAccessor = mappingManager.createAccessor(ActivityAccessor.class);
  }

  @Override
  public void save(Activity activity) {
    activityMapper.save(activity);
  }

  @Override
  public List<Activity> findBySchoolId(UUID schoolId) {
    return activityAccessor.findBySchoolId(schoolId).all();
  }

  @Override
  public Activity findById(UUID activityId) {
    List<Activity> all = activityAccessor.findById(activityId).all();
    if (all != null && !all.isEmpty()) {
      return all.iterator().next();
    }
    return null;
  }

}
