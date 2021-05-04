package com.akvitko.person.service;

import com.akvitko.person.entity.PersonEntity;
import com.akvitko.person.exception.AppException;
import com.akvitko.person.model.ApiResponse;
import com.akvitko.person.model.BirthFilter;
import com.akvitko.person.model.Person;
import com.akvitko.person.repository.PersonRepository;
import com.akvitko.person.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by a.kvitko
 * Класс реализующий бизнес-логику
 */

@Service
public class PersonService {

    private static final String SEARCH_NOT_FOUND = "Данных удовлетворяющих критериям поиска не найдено";
    private static final String PERSON_NOT_FOUND = "Человека с запрашиваемым id в БД не существует";

    @Autowired
    PersonRepository personRepo;

    /**
     * Добавление персоны в БД
     *
     * @param - Person
     * @return ApiResponse<Person>
     */
    public ApiResponse< Person > addNewPerson( Person person ) {
        ApiResponse< Person > response = new ApiResponse<>();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( MapUtils.toModel( personRepo.save( MapUtils.toEntity( person ) ) ) );
        } catch ( Exception e ) {
            response.setStatus( HttpStatus.INTERNAL_SERVER_ERROR.value() );
            response.setMessage( e.getMessage() );
        }
        return response;
    }

    /**
     * Получение персоны из БД по id
     *
     * @param id - int - id персоны
     * @return ApiResponse<Person>
     */
    public ApiResponse< Person > getPersonById( long id ) {
        ApiResponse< Person > response = new ApiResponse<>();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( MapUtils.toModel( personRepo.findById( id ).orElseThrow( AppException::new ) ) );
        } catch ( Exception e ) {
            response.setStatus( HttpStatus.NOT_FOUND.value() );
            response.setMessage( e instanceof AppException ? PERSON_NOT_FOUND : e.getMessage() );
        }
        return response;
    }

    /**
     * Метод обрабочик сетевого запроса на поиск персон по дате рождения
     *
     * @param filter - BirthFilter даты начала и конца поиска в формате ( дд.мм.гггг )
     * @return ApiResponse<List < Person> >
     */
    public ApiResponse< List< Person > > searchPersons( BirthFilter filter ) {
        ApiResponse< List< Person > > response = new ApiResponse<>();
        response.setStatus( HttpStatus.OK.value() );
        try {
            response.setResult( personRepo.findAllByBirthdayBetween( filter.getFromAsDate(), filter.getToAsDate() ).stream().map( pe -> {
                try {
                    return MapUtils.toModel( pe );
                } catch ( AppException e ) {
                    e.printStackTrace();
                }
                return null;
            } ).collect( Collectors.toList() ) );
            if ( response.getResult() == null || response.getResult().size() == 0 ) {
                response.setStatus( HttpStatus.NOT_FOUND.value() );
                response.setMessage( SEARCH_NOT_FOUND );
            }
        } catch ( Exception e ) {
            response.setStatus( HttpStatus.INTERNAL_SERVER_ERROR.value() );
            response.setMessage( e.getMessage() );
        }
        return response;
    }


}
