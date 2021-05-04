package com.akvitko.person.rest;

import com.akvitko.person.model.ApiResponse;
import com.akvitko.person.model.BirthFilter;
import com.akvitko.person.model.Person;
import com.akvitko.person.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by a.kvitko
 * Класс - компонент занимающийся обработкой сетевых запросов.
 */

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE )
@Api( tags = {"PersonController"} )
public class PersonController {

    @Autowired
    PersonService personService;


    /**
     * Метод обрабочик сетевого запроса на добавление персоны в БД
     *
     * @param - Person
     * @return ApiResponse<Person>
     */
    @ApiOperation( value = "Метод обрабочик сетевого запроса на добавление персоны в БД", response = ApiResponse.class )
    @PostMapping( "/persons" )
    public ApiResponse< Person > addPerson( @RequestBody Person person ) {
        return personService.addNewPerson( person );
    }

    /**
     * Метод обрабочик сетевого запроса на получение персоны по id
     *
     * @param id - int - id персоны
     * @return ApiResponse<Person>
     */
    @ApiOperation( value = "Метод обрабочик сетевого запроса на получение персоны по id", response = ApiResponse.class )
    @GetMapping( "/persons/{id}" )
    public ApiResponse< Person > getPersonById( @ApiParam( value = "By default exist 1 and 2" ) @PathVariable( "id" ) int id ) {
        return personService.getPersonById( id );
    }

    /**
     * Метод обрабочик сетевого запроса на поиск персон по дате рождения
     *
     * @param birthFilter - BirthFilter даты начала и конца поиска в формате ( дд.мм.гггг )
     * @return ApiResponse<List < Person> >
     */
    @ApiOperation( value = "Метод обрабочик сетевого запроса на поиск персон по дате рождения", response = ApiResponse.class )
    @PostMapping( "/persons/search" )
    public ApiResponse< List< Person > > searchPersons( @RequestBody BirthFilter birthFilter ) {
        return personService.searchPersons( birthFilter );
    }

}
