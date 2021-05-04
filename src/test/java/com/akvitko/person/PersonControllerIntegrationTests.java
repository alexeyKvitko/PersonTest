package com.akvitko.person;

import com.akvitko.person.model.ApiResponse;
import com.akvitko.person.model.BirthFilter;
import com.akvitko.person.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest( classes = PersonApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class PersonControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql( {"classpath:data.sql"} )
    @Test
    public void addNewPersonTest() {
        Person testPerson = new Person( "Юрий", "Алексеевич", "Гагарин", "09.03.1934" );
        ResponseEntity< ApiResponse > responseEntity
                = this.restTemplate.postForEntity( "http://localhost:" + port + "/api/persons", testPerson, ApiResponse.class );
        assertTrue( HttpStatus.OK.value() == responseEntity.getBody().getStatus() );
    }

    @Sql( {"classpath:data.sql"} )
    @Test
    public void getPersonByIdTest() {
        ApiResponse<Person> response= this.restTemplate.getForObject("http://localhost:" + port + "/api/persons/{id}", ApiResponse.class,1);
        assertTrue( HttpStatus.OK.value() == response.getStatus() );
    }

    @Sql( {"classpath:data.sql"} )
    @Test
    public void searchPersonsTest() {
        BirthFilter filter = new BirthFilter( "01.01.1980", "01.01.2020" );
        ResponseEntity< ApiResponse > responseEntity
                = this.restTemplate.postForEntity( "http://localhost:" + port + "/api/persons/search", filter, ApiResponse.class );
        assertTrue( HttpStatus.OK.value() == responseEntity.getBody().getStatus() );
        assertTrue( (( List ) responseEntity.getBody().getResult()).size() == 1 );
    }


}
