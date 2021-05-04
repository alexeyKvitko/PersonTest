package com.akvitko.person.exception;

/**
 * Created by a.kvitko
 *  Класс расширяющий (наследующий) встроенный класс Exception.
 */
public class AppException extends Exception{

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String p_arg0, Throwable p_arg1) {
        super(p_arg0, p_arg1);
    }

    public AppException(Throwable p_arg0) {
        super(p_arg0);
    }

}
