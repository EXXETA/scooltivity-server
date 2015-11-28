package de.exxeta.scooltivity.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchoolAlreadyExistException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(SchoolAlreadyExistException.class);

  public SchoolAlreadyExistException(String schoolName) {
    super("School already exist for school name " + schoolName);
    logger.error(super.getMessage());
  }

}
