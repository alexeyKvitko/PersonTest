package com.akvitko.person.utils;

import com.akvitko.person.exception.AppException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by a.kvitko
 * Класс содержащий вспомогательные методы для работы с датой
 */

public abstract class DateUtils {

    public static final String DATE_FORMAT_EXCEPTION = "Ошибка преобразования. Дата рождения должна быть в формате: дд.мм.гггг";

    public static final ThreadLocal< DateFormat > SIMPLE_PATTERN_BY_DOT = ThreadLocal.withInitial( () -> new SimpleDateFormat( "dd.MM.yyyy", new Locale( "ru" ) ) );

    /**
     * Возвращает строку из даты
     *
     * @param fmt  - Форматы в котором возвращается строка
     * @param date - Дата котороая будет преобразована в строку
     * @return String
     * @throws AppException
     */
    public static String formatDate( ThreadLocal< DateFormat > fmt, Date date ) throws AppException {
        String result = null;
        if ( date != null ) {
            try {
                result = fmt.get().format( date );
            } catch ( Exception ex ) {
                throw new AppException( DATE_FORMAT_EXCEPTION );
            }
        }
        return result;
    }


    /**
     * Возвращает дату из строки
     *
     * @param fmt     - Форматы в котором ожидается строка
     * @param strDate - Дата в виде строки, котороая будет преобразована в тип Date
     * @return Date
     * @throws AppException
     */
    public static Date parseDate( ThreadLocal< DateFormat > fmt, String strDate ) throws AppException {
        Date result = null;
        if ( strDate != null ) {
            try {
                result = fmt.get().parse( strDate );
            } catch ( Exception ex ) {
                throw new AppException( DATE_FORMAT_EXCEPTION );
            }
        }
        return result;
    }

    /**
     * Возвращает дату из строки
     *
     * @param strDate - Дата в виде строки, ожидаемой в формате дд.мм.гггг, котороая будет преобразована в тип Date
     * @return Date
     */
    public static Date parseDate( String strDate ) {
        Date result = null;
        try {
            result = SIMPLE_PATTERN_BY_DOT.get().parse( strDate );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }

}
