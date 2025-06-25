package com.project.expenses.Controller;

import com.project.expenses.Model.Person;
import com.project.expenses.Repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Add new person
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/user/{userId}")
    public List<Person> getPersonsByUser(@PathVariable Long userId) {
        return personRepository.findByUserId(userId);
    }


    // Update person
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setName(updatedPerson.getName());
            person.setPhone(updatedPerson.getPhone());
            person.setRelation(updatedPerson.getRelation());
            return personRepository.save(person);
        } else {
            throw new RuntimeException("Person not found with id " + id);
        }
    }

    // Delete person
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}