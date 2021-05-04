package com.akvitko.person.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by a.kvitko
 *  Модель данных - сущность описывающая персону, передаваемую в ответе на запрос
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private Long id;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String birthday;

    public Person( Long id ) {
        this.id = id;
    }

    public Person( String firstName, String patronymic, String lastName, String birthday ) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
