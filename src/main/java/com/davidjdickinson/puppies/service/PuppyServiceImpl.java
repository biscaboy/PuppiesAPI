package com.davidjdickinson.puppies.service;


import com.davidjdickinson.puppies.exception.PuppyNotFoundException;
import com.davidjdickinson.puppies.repository.PuppyRepository;
import com.davidjdickinson.puppies.entity.Puppy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuppyServiceImpl implements PuppyService {

    @Autowired
    PuppyRepository puppyRepository;

    public List<Puppy> retrievePuppies(){
        return (List<Puppy>) puppyRepository.findAll();
    }

    @Override
    public List<String> retrievePuppyBreeds() {
        return (List<String>) puppyRepository.findAllBreed();
    }

    @Override
    public String retrievePuppyIdAndBreedById(Long id) {
        Optional<String> optionalBreed =  Optional.ofNullable(puppyRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(PuppyNotFoundException::new);
        return breed;
    }

    @Override
    public List<String> retrievePuppyNames() {
        return (List<String>) puppyRepository.findAllName();
    }

}

