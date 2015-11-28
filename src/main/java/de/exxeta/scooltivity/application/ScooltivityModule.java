package de.exxeta.scooltivity.application;

import com.google.inject.AbstractModule;

import de.exxeta.scooltivity.account.service.AccountService;
import de.exxeta.scooltivity.account.service.AccountServiceImpl;
import de.exxeta.scooltivity.account.service.ActivityService;
import de.exxeta.scooltivity.account.service.ActivityServiceImpl;
import de.exxeta.scooltivity.persistence.dao.AccountDao;
import de.exxeta.scooltivity.persistence.dao.AccountDaoImpl;
import de.exxeta.scooltivity.persistence.dao.ActivityByAccountDao;
import de.exxeta.scooltivity.persistence.dao.ActivityByAccountImpl;
import de.exxeta.scooltivity.persistence.dao.ActivityDao;
import de.exxeta.scooltivity.persistence.dao.ActivityDaoImpl;
import de.exxeta.scooltivity.persistence.dao.SchoolDao;
import de.exxeta.scooltivity.persistence.dao.SchoolDaoImpl;

public class ScooltivityModule extends AbstractModule {

  @Override
  protected void configure() {
    // bind(AuthenticationRequestFilter.class);
    bind(AccountDao.class).to(AccountDaoImpl.class);
    bind(ActivityDao.class).to(ActivityDaoImpl.class);
    bind(SchoolDao.class).to(SchoolDaoImpl.class);
    bind(ActivityByAccountDao.class).to(ActivityByAccountImpl.class);
    bind(AccountService.class).to(AccountServiceImpl.class);
    bind(ActivityService.class).to(ActivityServiceImpl.class);
  }

}
