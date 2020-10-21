package com.davidjdickinson.puppies.web;

import com.davidjdickinson.puppies.service.PuppyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PuppyController.class)
public class PuppiesControllerUnitTests {

        private static String encoding;

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        PuppyService puppyService;

        @BeforeAll
        public static void beforeAll() {
            PuppiesControllerUnitTests.encoding = Base64.getEncoder().encodeToString(("admin" + ":" + "password").getBytes());
        }

        @Test
        public void getAllPuppies() throws Exception {

            mockMvc.perform(get("/puppies")
                    .header("Authorization", "BASIC " + encoding))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json("[]"));

            verify(puppyService, times(1)).retrievePuppies();
        }

        @Test
        public void getPuppyIdAndBreedById() throws Exception {
            mockMvc.perform(get("/puppy/1/breed")
                    .header("Authorization", "BASIC " + encoding))
                    .andExpect(status().isOk());

            verify(puppyService, times(1)).retrievePuppyIdAndBreedById(Long.valueOf(1));
        }
}
