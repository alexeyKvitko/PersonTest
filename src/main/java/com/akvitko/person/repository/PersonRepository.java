package com.akvitko.person.repository;

import com.akvitko.person.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by a.kvitko
 *  Интерфейс, который предоставляет CRUD методы работы с БД
 *
 */
public interface PersonRepository  extends CrudRepository< PersonEntity, Long > {

      List<PersonEntity> findAllByBirthdayBetween( Date from, Date to );
}
