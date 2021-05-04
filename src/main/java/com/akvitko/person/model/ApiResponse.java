package com.akvitko.person.model;

import java.io.Serializable;

/**
 * Created by a.kvitko
 *  Модель данных - сущность возвращаемая в ответе на запрос
 */

public class ApiResponse< T > implements Serializable {

    private int status;
    private String message;
    private T result;

    public ApiResponse() {
    }

    public ApiResponse( int status, String message, T result ) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus( int status ) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult( T result ) {
        this.result = result;
    }

}
