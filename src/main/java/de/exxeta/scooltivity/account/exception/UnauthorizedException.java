package de.exxeta.scooltivity.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnauthorizedException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(UnauthorizedException.class);

  public UnauthorizedException(String message) {
    super(message);
    logger.error(message);
  }
}
