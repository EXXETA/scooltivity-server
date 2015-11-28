package de.exxeta.scooltivity.persistence.dao;

import java.util.List;
import java.util.UUID;

import de.exxeta.scooltivity.persistence.model.ActivityByAccount;

public interface ActivityByAccountDao {

  public ActivityByAccount getOne(UUID activityId, UUID accountId);

  public void save(ActivityByAccount activityByAccount);

  public void delete(UUID accountId, UUID activityId);

  public List<UUID> findByAccountId(UUID accountId);

}
