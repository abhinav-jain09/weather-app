package com.assignment.spring.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotValidInput extends RuntimeException {
    private String message;
}
