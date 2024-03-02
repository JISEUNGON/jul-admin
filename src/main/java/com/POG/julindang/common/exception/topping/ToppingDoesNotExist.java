package com.POG.julindang.common.exception.topping;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ToppingDoesNotExist extends RuntimeException{
    String message;
    public ToppingDoesNotExist(Long toppingId) {
        super();
        this.message = toppingId.toString();
    }
}
