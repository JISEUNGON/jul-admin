package com.POG.julindang.common.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidArgsException extends RuntimeException {
    private String message;

    public InvalidArgsException(final String s) {
        super(s);
        this.message = s;
    }
}
