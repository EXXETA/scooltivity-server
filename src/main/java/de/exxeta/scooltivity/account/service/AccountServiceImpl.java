package de.exxeta.scooltivity.account.service;

import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Base64;
import java.util.UUID;

import javax.inject.Inject;

import com.nimbusds.jose.JOSEException;

import de.exxeta.scooltivity.account.exception.AccountAlreadyExistException;
import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.account.exception.UnauthorizedException;
import de.exxeta.scooltivity.account.util.PasswordUtils;
import de.exxeta.scooltivity.account.util.TokenUtils;
import de.exxeta.scooltivity.application.ScooltivityConfiguration;
import de.exxeta.scooltivity.persistence.dao.AccountDao;
import de.exxeta.scooltivity.persistence.dao.SchoolDao;
import de.exxeta.scooltivity.persistence.model.Account;
import de.exxeta.scooltivity.persistence.model.Login;
import de.exxeta.scooltivity.persistence.model.School;
import de.exxeta.scooltivity.persistence.model.Token;

public class AccountServiceImpl implements AccountService {

  @Inject
  private AccountDao accountDao;

  @Inject
  private SchoolDao schoolDao;

  @Inject
  private ScooltivityConfiguration configuration;

  public AccountServiceImpl() {
  }

  @Override
  public Login registerAccount(Account account) throws AccountAlreadyExistException, GeneralSecurityException, IOException, JOSEException,
      ParseException, UnauthorizedException, AccountNotFoundException, SchoolNotFoundException {

    School school = schoolDao.getOne(account.getSchoolName());
    if (school == null) {
      school = new School(account.getSchoolName());
      schoolDao.save(school);
    }
    account.setSchoolId(school.getSchoolId());

    if (accountDao.getOne(account.getEmail(), school.getSchoolId()) != null) {
      throw new AccountAlreadyExistException("Account already exists for email=" + account.getEmail());
    }

    if (account.getPassword() != null) {
      String passwordAsHash = PasswordUtils.createPasswordHash(account.getPassword());
      String passwordSalt = PasswordUtils.createPasswordHash(UUID.randomUUID().toString());
      account.setPassword(PasswordUtils.createPasswordHash(passwordAsHash, passwordSalt));
      account.setPasswordSalt(passwordSalt);
    }

    if (account.getToken() != null) {
      if (!TokenUtils.verifyToken(account.getToken(), configuration.getSharedSecretString())) {
        throw new UnauthorizedException("Authentication failed for account creation!");
      }
      account.setRegisteredBy(getAccountIdFromToken(account.getToken()));
    }

    Login login = createLogin(account, school);

    account.setToken(login.getToken());
    accountDao.save(account);

    return login;
  }

  @Override
  public Account getAccount(String email, String schoolName) throws SchoolNotFoundException {
    School school = getSchool(schoolName);
    Account account = accountDao.getOne(email, school.getSchoolId());
    account.setSchoolName(school.getSchoolName());
    return account;
  }

  private School getSchool(String schoolName) throws SchoolNotFoundException {
    School school = schoolDao.getOne(schoolName);
    if (school == null) {
      throw new SchoolNotFoundException(schoolName);
    }
    return school;
  }

  @Override
  public Login login(String email, String schoolName, String password) throws UnauthorizedException, GeneralSecurityException, IOException,
      JOSEException, SchoolNotFoundException {
    Account account = accountDao.getOne(email, getSchool(schoolName).getSchoolId());
    if (account == null) {
      throw new UnauthorizedException("Authentication failed for email=" + email);
    }

    // check password
    String passwordAsHash = PasswordUtils.createPasswordHash(password);
    if (!account.getPassword().equals(PasswordUtils.createPasswordHash(passwordAsHash, account.getPasswordSalt()))) {
      throw new UnauthorizedException("Incorrect password for email=" + email);
    }

    School school = getSchool(schoolName);

    return createLogin(account, school);
  }

  private Login createLogin(Account account, School school) throws IOException, GeneralSecurityException, JOSEException {
    Token payload = new Token(account, school);
    String token = TokenUtils.generateToken(payload, configuration.getSharedSecretString());
    return new Login(token, account.getType().name());
  }

  private UUID getAccountIdFromToken(String tokenAsString) throws IOException, GeneralSecurityException, JOSEException, ParseException,
      UnauthorizedException, AccountNotFoundException, SchoolNotFoundException {
    if (!TokenUtils.verifyToken(tokenAsString, configuration.getSharedSecretString())) {
      throw new UnauthorizedException("Invalid token for registration!");
    }
    Token token = Jackson.newObjectMapper().readValue(Base64.getDecoder().decode(tokenAsString.split("\\.")[1]), Token.class);
    Account registeredByAccount = accountDao.getOne(token.getEmail(), getSchool(token.getSchoolName()).getSchoolId());
    if (registeredByAccount == null) {
      throw new AccountNotFoundException(token.getEmail(), token.getSchoolName());
    }
    return registeredByAccount.getAccountId();
  }
}
