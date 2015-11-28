package de.exxeta.scooltivity.persistence.dao;

import javax.inject.Inject;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import de.exxeta.scooltivity.persistence.model.School;

public class SchoolDaoImpl implements SchoolDao {

  private Mapper<School> schoolMapper;

  @Inject
  public SchoolDaoImpl(MappingManager mappingManager) {
    this.schoolMapper = mappingManager.mapper(School.class);
  }

  @Override
  public School getOne(String schoolName) {
    return schoolMapper.get(schoolName);
  }

  @Override
  public void save(School school) {
    schoolMapper.save(school);
  }

}
