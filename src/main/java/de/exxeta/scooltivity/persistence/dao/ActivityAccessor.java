package de.exxeta.scooltivity.persistence.dao;

import java.util.UUID;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import de.exxeta.scooltivity.persistence.model.Activity;

@Accessor
public interface ActivityAccessor {

  @Query("SELECT * FROM scooltivity.activity where school_id = :schoolId")
  public Result<Activity> findBySchoolId(@Param("schoolId") UUID schoolId);

  @Query("SELECT * FROM scooltivity.activity where activity_id = :activityId")
  public Result<Activity> findById(@Param("activityId") UUID activityId);
}
