package com.POG.julindang.common.exception.cafe;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CafeIdDoesNotExist extends RuntimeException{
    public CafeIdDoesNotExist() {
        super();
    }
}
