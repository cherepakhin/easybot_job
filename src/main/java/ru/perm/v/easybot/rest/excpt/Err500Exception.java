package ru.perm.v.easybot.rest.excpt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Err500Exception extends RuntimeException {
    public Err500Exception(String message) {
        super(message);
    }
}