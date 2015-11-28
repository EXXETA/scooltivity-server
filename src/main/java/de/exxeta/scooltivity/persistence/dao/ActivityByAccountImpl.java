package de.exxeta.scooltivity.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import de.exxeta.scooltivity.persistence.model.ActivityByAccount;

public class ActivityByAccountImpl implements ActivityByAccountDao {

  private Mapper<ActivityByAccount> mapper;

  private ActivityByAccountAccessor accessor;

  @Inject
  public ActivityByAccountImpl(MappingManager mappingManager) {
    mapper = mappingManager.mapper(ActivityByAccount.class);
    accessor = mappingManager.createAccessor(ActivityByAccountAccessor.class);
  }

  @Override
  public ActivityByAccount getOne(UUID activityId, UUID accountId) {
    return mapper.get(activityId, accountId);
  }

  @Override
  public void save(ActivityByAccount activityByAccount) {
    mapper.save(activityByAccount);

  }

  @Override
  public void delete(UUID accountId, UUID activityId) {
    accessor.delete(accountId, activityId);

  }

  @Override
  public List<UUID> findByAccountId(UUID accountId) {
    List<Row> all = accessor.findByAccountId(accountId).all();
    List<UUID> result = new ArrayList<UUID>(all.size());
    for (Row row : all) {
      result.add(row.getUUID(0));
    }
    return result;
  }

}
