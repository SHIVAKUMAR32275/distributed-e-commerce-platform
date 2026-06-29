package com.example.distributed_e_commerence_platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryNotFoundException(CategoryNotFoundException ex){

        ErrorResponseDto responseDto = new ErrorResponseDto( HttpStatus.NOT_FOUND.value(),ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleProductNotFoundException(
            ProductNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            InventoryNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            UserNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


     @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            AddressNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }

     @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            OrderNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


 @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            PaymentNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }



 @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
         ReviewNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


 @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
         CartNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            CartItemNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            NotificationNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }
 @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
         UserAlreadyExistException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
                    RoleNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


    @ExceptionHandler(UserNotSignedUpException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            UserNotSignedUpException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }



    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
            PasswordIncorrectException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


 @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>
    handleInventoryNotFoundException(
         SessionNotFoundException ex) {

        ErrorResponseDto responseDto =
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }



}

