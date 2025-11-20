package com.prueba_tecnica.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String errorDetails;

    public ErrorResponse(String message, int status, String errorDetails) {
        this.message = message;
        this.status = status;
        this.errorDetails = errorDetails;
    }

}
