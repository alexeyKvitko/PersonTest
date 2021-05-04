package com.akvitko.person.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by a.kvitko
 *  Сущность для хранения данных о персоне в БД
 */
@Getter
@Setter
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column( name="first_name")
    private String firstName;

    @Column( name="patronymic")
    private String patronymic;

    @Column( name="last_name")
    private String lastName;

    @Column( name="birthday")
    private Date birthday;



}
