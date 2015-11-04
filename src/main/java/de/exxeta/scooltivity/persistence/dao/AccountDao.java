package de.exxeta.scooltivity.persistence.dao;

import java.util.List;

import de.exxeta.scooltivity.persistence.model.Account;

public interface AccountDao {

  public void save(Account account);

  public List<Account> findAll();

}
