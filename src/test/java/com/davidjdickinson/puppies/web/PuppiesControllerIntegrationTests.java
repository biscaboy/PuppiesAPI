package com.davidjdickinson.puppies.web;

import com.davidjdickinson.puppies.entity.Puppy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PuppiesControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * This test method was made possible by this post by javaspringframeworkcourse.
     *
     * I am so happy they posted this solution, I cannot tell you.  Amazed it works.
     *
     * https://javaspringframeworkcourse.wordpress.com/2015/08/06/spring-mvc-resttemplate-getforentity-returns-list-of-objects/
     *
     * @RequestMapping(value = "/objects", method = RequestMethod.GET)
     * public List<Object> getObjects() {
     *
     * ResponseEntity<Object[]> response = restTemplate.getForEntity(
     * "http://url", Object[].class);
     *
     *  return Arrays.asList(response.getBody());
     * }
     */
    @Test
    public void getAllPuppies() {
        ResponseEntity<Puppy[]> response =
                this.restTemplate
                        .withBasicAuth("admin", "password")
                        .getForEntity("http://localhost:" + port + "/puppies", Puppy[].class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    /**
     * The TestRestTemplate.withBasicAuth() method was explained on this page:
     * https://www.baeldung.com/spring-boot-testresttemplate
     */
    @Test
    public void getPuppyIdAndBreedByPuppyId() {
        ResponseEntity<String> response =
                this.restTemplate
                        .withBasicAuth("admin", "password")
                        .getForEntity("http://localhost:" + port + "/puppy/1/breed", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
