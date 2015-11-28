package de.exxeta.scooltivity.persistence.dao;

import de.exxeta.scooltivity.persistence.model.School;

public interface SchoolDao {

  public School getOne(String schoolName);

  public void save(School school);

}
