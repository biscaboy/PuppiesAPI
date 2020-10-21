package com.davidjdickinson.puppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Puppy not found")
public class PuppyNotFoundException extends RuntimeException  {

    private Map<String, Object> extensions = new HashMap<>();

    public PuppyNotFoundException() {
    }

    public PuppyNotFoundException(String message) {
        super(message);
    }

    public PuppyNotFoundException(String message, Long invalidPuppyId) {
        super(message);
        extensions.put("invalidPuppyId", invalidPuppyId);
    }

}