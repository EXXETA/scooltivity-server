package de.exxeta.scooltivity.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchoolNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(SchoolNotFoundException.class);

  public SchoolNotFoundException(String schoolName) {
    super("School not found for school name" + schoolName);
    logger.error(super.getMessage());
  }
}
