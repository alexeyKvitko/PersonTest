package com.akvitko.person.model;


import com.akvitko.person.exception.AppException;
import com.akvitko.person.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

/**
 * Created by a.kvitko
 *  Модель данных - сущность описывающая фильтр, для поиска по дате
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BirthFilter {


    private static final Date MIN_DATE =  DateUtils.parseDate( "01.01.1920" ) ;
    private static final Date MAX_DATE =  DateUtils.parseDate( "01.01.2021" ) ;
    private static final String MIN_DATE_MSG = "Дата начала поиска меньше 01.01.1920";
    private static final String MAX_DATE_MSG = "Дата начала поиска больше 01.01.2015";

    private String fromDate;
    private String toDate;

    public Date getFromAsDate() throws AppException {
        Date from = DateUtils.parseDate( DateUtils.SIMPLE_PATTERN_BY_DOT, fromDate );
        if ( from.before( MIN_DATE ) ) {
            throw new AppException( MIN_DATE_MSG );
        }
        return from;
    }

    public Date getToAsDate() throws AppException {
        Date to = DateUtils.parseDate( DateUtils.SIMPLE_PATTERN_BY_DOT, toDate );
        if ( to.after( MAX_DATE ) ) {
            throw new AppException( MAX_DATE_MSG );
        }
        return to;
    }


}
