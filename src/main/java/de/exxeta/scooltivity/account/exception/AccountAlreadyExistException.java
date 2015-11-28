package de.exxeta.scooltivity.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountAlreadyExistException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(AccountAlreadyExistException.class);

  public AccountAlreadyExistException(String message) {
    super(message);
    logger.error(message);
  }

}
