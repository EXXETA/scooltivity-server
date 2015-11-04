package de.exxeta.scooltivity.persistence.dao;

import java.util.List;

import javax.inject.Inject;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import de.exxeta.scooltivity.persistence.model.Account;

public class AccountDaoImpl implements AccountDao {

  private Mapper<Account> accountMapper;
  private AccountAccessor accountAccessor;

  @Inject
  public AccountDaoImpl(MappingManager mappingManager) {
    accountMapper = mappingManager.mapper(Account.class);
    accountAccessor = mappingManager.createAccessor(AccountAccessor.class);
  }

  @Override
  public void save(Account account) {
    accountMapper.save(account);
  }

  @Override
  public List<Account> findAll() {
    return accountAccessor.findAll().all();
  }

}
