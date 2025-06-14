package com.project.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatusCode httpStatusCode;
    private final HttpHeaders httpHeaders;

    public HttpException(HttpStatusCode httpStatusCode, HttpHeaders httpHeaders) {
        super(String.format("Error: statusCode - %s, headers - %s", httpStatusCode, httpHeaders));
        this.httpStatusCode = httpStatusCode;
        this.httpHeaders = httpHeaders;
    }

    public HttpException(String errorMessage) {
        super(errorMessage);
        this.httpStatusCode = null;
        this.httpHeaders = null;
    }

    public HttpException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        this.httpStatusCode = null;
        this.httpHeaders = null;
    }

    // GETTERY:
    public HttpStatusCode getStatusCode() {
        return httpStatusCode;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }
}