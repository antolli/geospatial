package br.com.sccon.geospatial.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;

public class Response4xxException extends RestClientResponseException {
    private static final long serialVersionUID = 1L;

    public Response4xxException(String msg, HttpStatus httpStatus){
        super(msg, httpStatus, "",
                (HttpHeaders) null, (byte[]) null, (Charset) null);
    }
}
