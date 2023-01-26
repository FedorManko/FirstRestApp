package ru.manko.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manko.FirstRestApp.models.Person;
import ru.manko.FirstRestApp.repositories.PeopleRepository;
import ru.manko.FirstRestApp.util.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id)  {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person){
        enrichPerson(person);
        peopleRepository.save(person);
    }
    private void enrichPerson(Person person) {
        person.setCreated_at(LocalDateTime.now());
        person.setUpdated_at(LocalDateTime.now());
        person.setCreated_who("ADMIN");
    }

}