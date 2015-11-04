package de.exxeta.scooltivity.persistence.dao;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import de.exxeta.scooltivity.persistence.model.Account;

@Accessor
public interface AccountAccessor {

  @Query("SELECT * FROM scooltivity.accounts")
  public Result<Account> findAll();

}
