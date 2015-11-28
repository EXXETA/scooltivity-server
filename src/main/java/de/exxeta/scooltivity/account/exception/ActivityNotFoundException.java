package de.exxeta.scooltivity.account.exception;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  private final Logger logger = LoggerFactory.getLogger(ActivityNotFoundException.class);

  public ActivityNotFoundException(UUID activityId) {
    super("Activity not found for id " + activityId);
    logger.error(super.getMessage());
  }

}
