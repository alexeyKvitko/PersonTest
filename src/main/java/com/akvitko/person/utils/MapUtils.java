package com.akvitko.person.utils;

import com.akvitko.person.entity.PersonEntity;
import com.akvitko.person.exception.AppException;
import com.akvitko.person.model.Person;

/**
 * Created by a.kvitko
 * Класс преобразующий данные о персоне
 */
public abstract class MapUtils {

    /**
     * Преобрзует сущность котороя хранится в БД в сущность которая возвращается в ответе на запрос
     *
     * @param personEntity -PersonEntity
     * @return Person
     */
    public static Person toModel( PersonEntity personEntity ) throws AppException {
        Person person = new Person();
        person.setId( personEntity.getId() );
        person.setFirstName( personEntity.getFirstName() );
        person.setPatronymic( personEntity.getPatronymic() );
        person.setLastName( personEntity.getLastName() );
        person.setBirthday( DateUtils.formatDate( DateUtils.SIMPLE_PATTERN_BY_DOT, personEntity.getBirthday() ) );
        return person;
    }

    /**
     * Преобрзует сущность из запроса , в сущность котороя хранится в БД
     *
     * @param person - Person
     * @return PersonEntity
     */
    public static PersonEntity toEntity( Person person ) throws AppException {
        PersonEntity personEntity = new PersonEntity();
        if ( person.getId() != null ) {
            personEntity.setId( person.getId() );
        }
        personEntity.setFirstName( person.getFirstName() );
        personEntity.setPatronymic( person.getPatronymic() );
        personEntity.setLastName( person.getLastName() );
        personEntity.setBirthday( DateUtils.parseDate( DateUtils.SIMPLE_PATTERN_BY_DOT, person.getBirthday() ) );
        return personEntity;
    }
}
