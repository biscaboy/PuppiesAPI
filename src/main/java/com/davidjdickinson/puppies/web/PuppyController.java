package com.davidjdickinson.puppies.web;

import com.davidjdickinson.puppies.entity.Puppy;
import com.davidjdickinson.puppies.service.PuppyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
        @ApiResponse(code=500, message = "The server is down. Please make sure that the Location microservice is running.")
})
public class PuppyController {

    private PuppyService puppyService;

    @Autowired
    public void setPuppyService(PuppyService puppyService){
        this.puppyService = puppyService;
    }

    @GetMapping("/puppies")
    public ResponseEntity<List<Puppy>> getPuppies() {
        List<Puppy> list = puppyService.retrievePuppies();
        return new ResponseEntity<List<Puppy>>(list, HttpStatus.OK);
    }

    @GetMapping("/puppy/{id}/breed")
    public ResponseEntity<String> getPuppyBreedById(@PathVariable Long id) {
        String breed = puppyService.retrievePuppyIdAndBreedById(id);
        return new ResponseEntity<String>(breed, HttpStatus.OK);
    }

    @GetMapping("/puppy/names")
    public ResponseEntity<List<String>> getPuppyNames() {
        List<String> list = puppyService.retrievePuppyNames();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/puppy/breeds")
    public ResponseEntity<List<String>> getPuppyBreedAndId() {
        List<String> list = puppyService.retrievePuppyBreeds();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }


}