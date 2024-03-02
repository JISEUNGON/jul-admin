package com.POG.julindang.common.exception.cafe;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class CafeDoesNotExist extends RuntimeException{
    String message;
    public CafeDoesNotExist(Long cafeId) {
        super(cafeId.toString());
        this.message = cafeId.toString();
    }
}

