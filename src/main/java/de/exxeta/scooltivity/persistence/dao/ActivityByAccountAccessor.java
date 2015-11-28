package de.exxeta.scooltivity.persistence.dao;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface ActivityByAccountAccessor {

  @Query("SELECT activity_id FROM scooltivity.activity_by_account WHERE account_id = :account_Id")
  public ResultSet findByAccountId(@Param("account_id") UUID account_id);

  @Query("DELETE FROM activity_by_account WHERE account_id = :account_id and activity_id = :activity_id;")
  public ResultSet delete(@Param("account_id") UUID account_id, @Param("activity_id") UUID activity_id);

}
