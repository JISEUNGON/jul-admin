package com.POG.julindang.common.exception.information;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class SugarDoesNotExist extends RuntimeException{
    public SugarDoesNotExist() {
        super();
    }
}
