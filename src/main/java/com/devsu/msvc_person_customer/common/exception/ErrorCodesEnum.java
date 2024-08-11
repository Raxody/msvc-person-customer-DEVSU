package com.devsu.msvc_person_customer.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {
    NOT_NULL("0001", HttpStatus.BAD_REQUEST, "The field %s cannot be empty."),
    NOT_VALUES_DIFFERENT_FROM_LETTERS("0002", HttpStatus.BAD_REQUEST, "The field %s cannot contain values other than letters."),
    IT_IS_NOT_AN_EMAIL("0003", HttpStatus.BAD_REQUEST, "The field %s does not have a valid email format, e.g., user@domain.com."),
    THE_LIST_CANNOT_BE_EMPTY("0004", HttpStatus.BAD_REQUEST, "The list of %s cannot be empty."),
    IT_IS_NOT_NUMERIC("0005", HttpStatus.BAD_REQUEST, "The field %s must be numeric."),
    THE_FIELD_CANNOT_BE_LESS_THAN_OR_EQUAL_TO_ZERO("0006", HttpStatus.BAD_REQUEST, "The field %s cannot be less than or equal to zero."),
    IT_MUST_NOT_MEET_WITH_THE_CONFIGURED_SPECIFICATIONS("0007", HttpStatus.BAD_REQUEST, "The field %s does not meet the configured specifications."),
    THE_CUSTOMER_IS_NOT_FOUND("0008", HttpStatus.BAD_REQUEST, "The customer with ID: %s is not found."),
    THE_CUSTOMER_IS_ALREADY_REGISTRED("0009", HttpStatus.BAD_REQUEST, "The customer with customer id: %s is already registred.");
    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCodesEnum(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}
