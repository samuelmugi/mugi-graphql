package com.mugigraphql.repos;

import com.mugigraphql.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mugi
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
