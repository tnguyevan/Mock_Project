package com.vti.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorResponseBase {
    FILE_NOT_SUPPORT(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "error.file_not_support"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "error.item_not_found"),
    NOT_EXISTED(HttpStatus.NOT_FOUND,"account ko ton tai"),
    IS_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR,"error.item_existed"),
    USER_NOT_EXISTED(HttpStatus.UNAUTHORIZED,"error.user_not_existed"),


    PASSWORD_INVALID(HttpStatus.INTERNAL_SERVER_ERROR, "error.password_invalid"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "error.Unauthorized"),
    FILM_TITLE_INVALID(HttpStatus.BAD_REQUEST,"Duplicate"),

    USER_BLOCKED(HttpStatus.FORBIDDEN,"user đã bị block"),
    FILM_DURATION_INVALID(HttpStatus.BAD_REQUEST,"Thời Lượng Không Hợp Lệ"),

    BAD_REQUEST(HttpStatus.FORBIDDEN,"error.bad_request"),
    INVALID_TIME(HttpStatus.INTERNAL_SERVER_ERROR,"error.invalid_time"),
    GROUP_CAN_NOT_UPDATE(HttpStatus.BAD_REQUEST, "error.not_update"),
    ROLE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "error.role_not_found"),
    ROLE_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR, "error.role_existed"),
    ITEM_NOT_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "error.item_not_update");


    public final HttpStatus status;
    public final String message;

    ErrorResponseBase(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
