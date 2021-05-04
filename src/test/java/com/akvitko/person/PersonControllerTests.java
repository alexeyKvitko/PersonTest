package com.akvitko.person;

import com.akvitko.person.model.ApiResponse;
import com.akvitko.person.model.BirthFilter;
import com.akvitko.person.model.Person;
import com.akvitko.person.rest.PersonController;
import com.akvitko.person.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class PersonControllerTests {

    @InjectMocks
    PersonController personController;

    @Mock
    PersonService personService;

    @Test
    public void addNewPersonTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes( new ServletRequestAttributes( request ) );

        ApiResponse< Person > personResponse = new ApiResponse<>();
        personResponse.setStatus( HttpStatus.OK.value() );
        personResponse.setResult( new Person( 1l ) );
        when( personService.addNewPerson( any( Person.class ) ) ).thenReturn( personResponse );
        Person testPerson = new Person( "Юрий", "Алексеевич", "Гагарин", "09.03.1934" );
        ApiResponse< Person > apiResponse = personController.addPerson( testPerson );

        assertThat( apiResponse.getStatus() ).isEqualTo( 200 );
        assertThat( apiResponse.getResult().getId() ).isEqualTo( 1 );
    }

    @Test
    public void getPersonByIdTest() {
        ApiResponse< Person > personResponse = new ApiResponse<>();
        personResponse.setStatus( HttpStatus.OK.value() );
        personResponse.setResult( new Person( 1l, "Юрий", "Алексеевич", "Гагарин", "09.03.1934" ) );
        when( personService.getPersonById( 1 ) ).thenReturn( personResponse );

        ApiResponse< Person > response = personController.getPersonById( 1 );
        assertThat( response.getStatus() ).isEqualTo( 200 );
        assertThat( response.getResult().getLastName() ).isEqualTo( "Гагарин" );
    }

    @Test
    public void searchPersonsTest() {
        ApiResponse< List< Person > > personResponse = new ApiResponse<>();
        personResponse.setStatus( HttpStatus.OK.value() );
        personResponse.setResult( new ArrayList< Person >() {{
            add( new Person( 1l , "Юрий", "Алексеевич", "Гагарин", "09.03.1934" ) );
        }} );
        BirthFilter filter = new BirthFilter("01.01.1920","01.01.2000");
        when( personService.searchPersons( filter ) ).thenReturn( personResponse );

        ApiResponse< List< Person > > response = personController.searchPersons( filter );
        assertThat( response.getStatus() ).isEqualTo( 200 );
        assertThat( response.getResult().size() ).isEqualTo( 1 );
        assertThat( response.getResult().get( 0 ).getFirstName() ).isEqualTo( "Юрий" );
    }


}
