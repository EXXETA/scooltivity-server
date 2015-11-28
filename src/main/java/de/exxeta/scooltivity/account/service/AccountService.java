package de.exxeta.scooltivity.account.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

import de.exxeta.scooltivity.account.exception.AccountAlreadyExistException;
import de.exxeta.scooltivity.account.exception.AccountNotFoundException;
import de.exxeta.scooltivity.account.exception.SchoolNotFoundException;
import de.exxeta.scooltivity.account.exception.UnauthorizedException;
import de.exxeta.scooltivity.persistence.model.Account;
import de.exxeta.scooltivity.persistence.model.Login;

public interface AccountService {

  public Login registerAccount(Account account) throws AccountAlreadyExistException, GeneralSecurityException,
      UnsupportedEncodingException, IOException, JOSEException, ParseException, UnauthorizedException, AccountNotFoundException,
      SchoolNotFoundException;

  public Account getAccount(String email, String schoolName) throws SchoolNotFoundException;

  public Login login(String email, String schoolName, String password) throws UnauthorizedException, GeneralSecurityException,
      UnsupportedEncodingException, IOException, JOSEException, SchoolNotFoundException;

}
