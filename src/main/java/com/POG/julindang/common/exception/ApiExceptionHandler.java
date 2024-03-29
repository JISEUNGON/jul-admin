package com.POG.julindang.common.exception;

import com.POG.julindang.common.exception.cafe.*;
import com.POG.julindang.common.exception.common.InvalidArgsException;
import com.POG.julindang.common.exception.information.*;
import com.POG.julindang.common.exception.member.MemberEmailNotFoundException;
import com.POG.julindang.common.exception.member.MemberIdNotFoundException;
import com.POG.julindang.common.exception.member.MemberLoginTypeNotExistException;
import com.POG.julindang.common.exception.member.MemberNotExistException;
import com.POG.julindang.common.exception.topping.ToppingDoesNotExist;
import com.POG.julindang.common.exception.topping.ToppingIdDoesNotExist;
import com.POG.julindang.common.exception.topping.ToppingNameDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * JEM
     * */
    @ExceptionHandler(MemberIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberIdNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-001", "Member id not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberEmailNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberEmailNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-002", "Member email is not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-003", "There is not member.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberLoginTypeNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberLoginTypeNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-004", "This login type is not exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgsException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidArgsException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-005", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * JEC
     */

    @ExceptionHandler(CafeNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CafeNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-001", "Cafe Name Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CafeDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CafeDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-002", "Cafe  Does Not Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BeverageNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(BeverageNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-003", "Beverage Name Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CafeIdDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CafeIdDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-004", "Cafe Id Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * common information in cafe and topping
     */
    @ExceptionHandler(CalorieDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CalorieDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-005", "Calorie Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ManagerDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(ManagerDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-006", "Manager Name Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ServeDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(ServeDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-007", "Serve Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SizeDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(SizeDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-008", "Size Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SugarDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(SugarDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-009", "Sugar Doesn't Exist : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * Topping
     */
    @ExceptionHandler(ToppingDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(ToppingDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JET-001", "Topping Doesn't Exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ToppingNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(ToppingNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JET-002", "Topping Name Doesn't Exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ToppingIdDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(ToppingIdDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JET-003", "Topping Id Doesn't Exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
