package com.example.demo.dao;
import java.util.*;
import com.example.demo.model.*;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional <Person> selectPersonbyId(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
