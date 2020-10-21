package com.davidjdickinson.puppies.service;


import com.davidjdickinson.puppies.entity.Puppy;

import java.util.List;

public interface PuppyService {

    List<Puppy> retrievePuppies();

    List<String> retrievePuppyBreeds();

    String retrievePuppyIdAndBreedById(Long id);

    List<String> retrievePuppyNames();
}
