package de.exxeta.scooltivity.persistence.dao;

import java.util.List;
import java.util.UUID;

import de.exxeta.scooltivity.persistence.model.Account;

public interface AccountDao {

  public void save(Account account);

  public Account getOne(String email, UUID schoolId);

  public List<Account> findAll();

}
