package com.davidjdickinson.puppies.repository;

import com.davidjdickinson.puppies.entity.Puppy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PuppyRepository extends CrudRepository<Puppy, Long> {

    @Query("select p.id, p.breed from Puppy p where p.id=:id")
    String findBreedById(Long id);

    @Query("select p.id, p.breed from Puppy p")
    List<String> findAllBreed();

    @Query("select p.id, p.name from Puppy p")
    List<String> findAllName();
}
