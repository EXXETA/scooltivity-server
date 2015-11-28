package de.exxeta.scooltivity.account.exception;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(AccountNotFoundException.class);

  public AccountNotFoundException(String email, String schoolName) {
    super("Account nor found for email: " + email + " school name: " + schoolName);
    logger.error(super.getMessage());
  }

  public AccountNotFoundException(String email, UUID schoolId) {
    super("Account nor found for email: " + email + " schoolId: " + schoolId);
    logger.error(super.getMessage());
  }

}
