package de.exxeta.scooltivity.persistence.dao;

import java.util.List;
import java.util.UUID;

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

  public void setAccountDao(AccountDao accountDao) {

  }

  @Override
  public void save(Account account) {
    accountMapper.save(account);
  }

  @Override
  public List<Account> findAll() {
    return accountAccessor.findAll().all();
  }

  @Override
  public Account getOne(String email, UUID schoolId) {
    return accountMapper.get(email, schoolId);
  }

}
