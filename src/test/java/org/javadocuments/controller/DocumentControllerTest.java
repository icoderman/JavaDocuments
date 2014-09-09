package org.javadocuments.controller;

import org.javadocuments.domain.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class DocumentControllerTest {

    private static String BASE_URI = "http://localhost:8080/document/";

    @Test
    public void  whenRetrievingADocument_thenCorrect() {
        final String URI = BASE_URI + "{id}";
        final RestTemplate restTemplate = new RestTemplate();
        final Document resource = restTemplate.getForObject(URI, Document.class, "16");
        assertThat(resource, notNullValue());
    }

    @Test
    public void givenConsumingJson_whenReadingTheDocument_thenCorrect() {
        final String URI = BASE_URI + "{id}";
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        final ResponseEntity<Document> response = restTemplate.exchange(URI, HttpMethod.GET, entity, Document.class, "16");
        final Document resource = response.getBody();
        assertThat(resource, notNullValue());
    }

    @Test
    public void givenConsumingJson_whenWritingTheDocument_thenCorrect() {
        final String URI = BASE_URI + "add";
        final RestTemplate restTemplate = new RestTemplate();
        final Document resource = new Document("TestName", "TestAuthor", "TestPath", "TestDescription");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType((MediaType.APPLICATION_JSON));
        final HttpEntity<Document> entity = new HttpEntity<Document>(resource, headers);
        final ResponseEntity<Document> response = restTemplate.exchange(URI, HttpMethod.POST, entity, Document.class);
        assertThat(response.getStatusCode(),  equalTo(HttpStatus.CREATED));
    }

}
